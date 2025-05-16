/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.coursework2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import uk.ac.keele.csc20004.Bench;
import uk.ac.keele.csc20004.RepairBay;
import uk.ac.keele.csc20004.RepairLine;
import uk.ac.keele.csc20004.RobotArena;
import uk.ac.keele.csc20004.robots.AbstractRobot;
import uk.ac.keele.csc20004.robots.HumanoidRobot;
import uk.ac.keele.csc20004.robots.Robot;


/**
 * REPORT 
 * 
 * There are many pontential issues especially when it comes to accessing the bench or repairline.
 * This involves multiple threads trying to access them at the same time which can result into crashes in the 
 * program. To solve this, I used blockingqueue structures which makes sure that there is correct concurrent access
 * and that the robots can do their cycle safely.
 * 
 * 
 * Service bays also had a similar issue in the case where multiple mechanics would try access the repair bay at
 * the same time which can also cause crashes. To solve this problem I used semaphore constuctors which makes 
 * sure that the mechanics provide the correct sempahore bay in order to be used. This was useful so the bay can
 * only be accessed by one thing and the others were denied permission until needed.
 * 
 * 
 * To prevent deadlock or livelock I made sure that the code works in a consistent pattern and with the use of
 * semaphores. Deadlock occurs when two threads are waiting for a response from eachother however, nothing happens.
 * This stops the program from running. Livelock is also when the threads keep changing states but not making
 * progress. It keeps going back and forth. So by using semaphores and blocking queues i was able to was 
 * prevent threads on eachother or blocking eachother and after each repair, the thread is released so the next
 * one is able to take its spot.
 * 
 * 
 * 
 * Starvation means when a thread is never prioritised so it never is able to do the action is must do like the
 * other threads. To prevent this, I made sure the the queue for the bench and repairline is created in a fair
 * manner so which ever thread comes first in line is served. This makes sure every thread gets a turn. Again,
 * using blocking queue and semaphores which makes sure each thread gets a chance to be repaired.
 * 
 * 
 * 
 * 
 */

/**
 * 
 * @author 23046671
 */
public class MyConcurrentArena extends RobotArena {

    private final List <Robot> arenaRobots = Collections.synchronizedList(new ArrayList<>());
    private final Map<String, Semaphore> repairBays = new HashMap<>();

    private final RepairLine repairLine;
    private final Bench bench;


    /**
     * This is the constructor for your class.
     * Please add the appropriate Javadoc to the class and its methods.
     * 
     * @param studentId please document this parameter
     * @param capacity please document this parameter
     * @param repairLine please document this parameter
     * @param bench please document this parameter
     */
    public MyConcurrentArena(int studentId, int capacity, RepairLine repairLine, Bench bench) {
        super(studentId, capacity, repairLine, bench);

        this.repairLine = repairLine;
        this.bench = bench;

        // semaphore created below with a permit of 1 so only one mechanic can access at a time.


        repairBays.put("frame", new Semaphore(1));
        repairBays.put("motor", new Semaphore(1));
        repairBays.put("sensor", new Semaphore(1));
        repairBays.put("actuator", new Semaphore(1));

    }

    @Override
    public void repair(Robot robot) {

    // only works with abstract robots, reject if not
    if (!(robot instanceof AbstractRobot)) {
        System.err.println("(Arena) Cannot repair: not an AbstractRobot");
        return;
    }

    AbstractRobot r = (AbstractRobot) robot;

    // checks for energy. If 0 recharge.
    if (r.getFrameEnergy() == 0.0) {
        new RepairBay("Frame").operate(SimulationParameters.BASE_ENERGY);
        System.out.println("(Arena) Frame repaired");
    }

    if (r.getMotorEnergy() == 0.0) {
        new RepairBay("Motor").operate(SimulationParameters.BASE_ENERGY);
        System.out.println("(Arena) Motors repaired");
    }

    if (r.getSensorsEnergy() == 0.0) {
        new RepairBay("Sensor").operate(SimulationParameters.BASE_ENERGY);
        System.out.println("(Arena) Sensors repaired");
    }

    if (r.getActuatorsEnergy() == 0.0) {
        new RepairBay("Actuator").operate(SimulationParameters.BASE_ENERGY);
        System.out.println("(Arena) Actuators repaired");
    }
}

        /** 
         * looks a highest energy in list of robots
         * */ 

    @Override
    public Robot getWinner() {

        synchronized (arenaRobots) {
            return arenaRobots.stream()
                    .max(Comparator.comparingDouble(Robot::getFrameEnergy))
                    .orElse(null);
        }
    }

    //simulation starts and creats robots to add to the arena
    public void start() {
        AtomicInteger robotCounter = new AtomicInteger(1);

        for (int i = 0; i < SimulationParameters.NUM_ROBOTS; i++) {
            Robot robot = new HumanoidRobot("R" + robotCounter.getAndIncrement());
            arenaRobots.add(robot);
        }

        for (int i = 0; i < SimulationParameters.NUM_MECHANICS; i++) {
            Thread mechanicThread = new Thread(new Mechanic(i, repairLine,bench, repairBays, this));
            mechanicThread.setName("Mechanic-" + i);
            mechanicThread.start();
        }

        for (int i = 0; i < SimulationParameters.NUM_BATTLEMANAGERS; i++) {
            Thread managerThread = new Thread(new BattleManager(i, arenaRobots, repairLine, bench));
            managerThread.setName("BattleManager-" + i);
            managerThread.start();
        }

        System.out.println("(Arena) Started with " + arenaRobots.size() + " robots.");
    }

    /**
     * create repairline and bench using my other classes.
     *  */ 
    public static void main(String[] args) {
        RepairLine repairLine = new MyRepairLine();
        Bench bench = new myBench();
        MyConcurrentArena arena = new MyConcurrentArena(12345, SimulationParameters.NUM_ROBOTS, repairLine, bench);
        arena.start();
    }
}


        
    
    


