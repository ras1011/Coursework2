package uk.ac.keele.csc20004.coursework2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import uk.ac.keele.csc20004.Bench;
import uk.ac.keele.csc20004.RepairLine;
import uk.ac.keele.csc20004.robots.Robot;
/**
 * @author 23046671
 */


 /** 
  * below is the variables
  */ 

public class Mechanic implements Runnable{
    private final int id;
    private final RepairLine repairLine;
    private final Bench bench ; 
    private final Map<String, Semaphore> repairBays ;
    private final MyConcurrentArena arena;


    /**
     *  constructor below for the mechanic
     */ 
    
    public Mechanic(int id, RepairLine repairLine, Bench bench, Map<String, Semaphore> repairBays, MyConcurrentArena arena) {
        this.id = id;
        this.repairLine = repairLine;
        this.bench = bench; 
        this.repairBays = repairBays;
        this.arena = arena;
    }
    
    /**
     *start a loop and add a robot to the repair queue 
     */ 
   @Override
public void run() {
    while (true) {
        Robot robot = repairLine.getNextRobot();
        System.out.println("[Mechanic " + id + "] Picked up robot for repair");

        // create a list for the repair bay and which are needed. Checks for energy levels
        List<String> neededBays = new ArrayList<>();
        if (robot.getFrameEnergy() == 0.0) neededBays.add("frame");
        if (robot.getMotorEnergy() == 0.0) neededBays.add("motor");
        if (robot.getSensorsEnergy() == 0.0) neededBays.add("sensor");
        if (robot.getActuatorsEnergy() == 0.0) neededBays.add("actuator");

        // blocks any object that doesnt have the semaphores needed.
        try {
            for (String bay : neededBays) {
                repairBays.get(bay).acquire();
            }

            // delay to prevent locking or crashes
            Thread.sleep((long) (Math.random() * SimulationParameters.OPERATION_DELAY));

            // Repair
            arena.repair(robot);

            // catches interruptions 
        } catch (InterruptedException e) {
            System.err.println("[Mechanic " + id + "] Interrupted while acquiring bays");
        } finally {
            // make sure to release mechanics
            for (String bay : neededBays) {
                repairBays.get(bay).release();
            }
        }

        // send robot to the bench.
        bench.addRobot(robot);
        System.out.println("[Mechanic " + id + "] Placed robot on bench");
    }
}
}
