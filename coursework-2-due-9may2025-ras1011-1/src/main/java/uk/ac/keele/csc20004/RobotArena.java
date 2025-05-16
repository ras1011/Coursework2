/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import uk.ac.keele.csc20004.robots.Robot;

/**
 * An abstract implementation of a generic robot arena.
 * It contains a data structure to hold the robots in the arena,
 * and expeccts to be given a repair line and a bench to manage the robots.
 * It also creates the four repair bays for the different types of parts.
 * The class also provides a method to print the stats of the arena.
 * * 
 * The class is abstract because it does not implement the full logic of the arena,
 * which is left as part of the coursework.
 * 
 * @author marcoortolani
 */
public abstract class RobotArena {
    protected final int studentId;

    private final int capacity;

    protected final LinkedList<Robot> robots = new LinkedList<>();
    protected final RepairLine repairLine;
    protected final Bench bench;

    protected final RepairBay frameBay = new RepairBay("Frame");
    protected final RepairBay motorBay = new RepairBay("Motor");
    protected final RepairBay sensorBay = new RepairBay("Sensor");
    protected final RepairBay actuatorBay = new RepairBay("Actuator");

    /**
     * The constructor defines the required parameters for the arena.
     * It creates the robots in the arena and adds them to the list.
     * 
     * The repair line and the bench are passed as parameters, so you will
     * need to provide your own implementation of these classes.
     * 
     * @param studentId your 8-digit student ID here (only used for some printout in the code)
     * @param capacity the max capacity of the arena (maximum number of robots it can hold)
     * @param repairLine the repair line
     * @param bench the bench
     */
    public RobotArena(int studentId, int capacity, RepairLine repairLine, Bench bench) {
        this.studentId = studentId;

        this.capacity = capacity;

        this.repairLine = repairLine;
        this.bench = bench;

    }

    /**
     * Returns the number of robots in the arena at the moment.
     * 
     * @return the number of robots in the arena
     */
    public int getNumRobots() {
        return robots.size();
    }

    /**
     * Returns the capacity of the arena (maximum number of robots it can hold).
     * 
     * @return the capacity of the arena
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the robot with the highest score in the arena.
     * The score is calculed based on the provided comparator.
     * 
     * @param comparator the comparator to use to calculate the score
     * @return the robot with the highest score
     */
    public Robot getWinner(Comparator<Robot> comparator) {
        return Collections.max(robots, comparator);
    }

    /**
     * Adds a robot to the arena.
     * 
     * @param r the robot to add
     */
    public void addRobot(Robot r) {
        if (robots.size() >= capacity) {
            throw new IllegalStateException("Arena is full");
        }
        robots.add(r);
    }

    /**
     * Returns the next pair of robots to engage in a combat.
     * At least two robots must be in the arena, otherwise an exception is thrown.
     * 
     * @return the pair of robots to fight
     */
    public CombatPair getOpponents() {
        if (robots.size() < 2) {
            throw new IllegalStateException("Not enough robots to fight");
        }
        return new CombatPair(robots.pop(), robots.pop());
    }

    /**
     * This method simulates the repair of a robot after it is
     * extracted from the repair line.
     * Only the components with 0 energy are repaired by using the
     * appropriate bay.
     * Implement your own version of this method in your
     * version of the RobotArena.
     * 
     * @param r the robot to repair
     */
    public abstract void repair(Robot r);

    /**
     * Returns the winner of the arena.
     * Implement your own version of this method in your
     * version of the RobotArena.
     * 
     * @return the winner of the arena
     */
    public abstract Robot getWinner();
    /**
     * Prints some useful stats about the arena.
     * It prints the robots in the arena, the robots in the repair line, and the robots on the bench.
     */
    public void printStats() {
        System.out.println("Stats for arena: " + studentId);

        System.out.println("Robots in arena:");
        for (Robot r : robots) {
            System.out.println(r);
        }

        System.out.println("Robots in repair line:");
        repairLine.printOut();

        System.out.println("Robots on bench:");
        bench.printOut();

    }   
}
