/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.coursework2;

/**
 * This class is provided for your convenience to store the main project
 * parameters all in one place.
 * You can change these values to test your code with different parameters.
 * 
 * @author marcoortolani
 */
public class SimulationParameters {
    /**
     * number of robots in the arena
     */
    public static final int NUM_ROBOTS = 15;

    /**
     * number of mechanics in the arena
     */
    public static final int NUM_MECHANICS = 3;

    /**
     * number of battle managers in the arena
     */
    public static final int NUM_BATTLEMANAGERS = 2;

    /** 
     * intensity of the strike when a robot attacks another robot
     * (will be weighted by the attack coefficient of the attacker
     * and the resistance coefficient of the defender)
     */
    public static final double STRIKE_INTENSITY = 10.0;

    /**
     * standard default energy of a part of a robot
     */
    public static final double BASE_ENERGY = 100.0;

    /**
     * Delay (in ms) used to simulate the time taken to fend off another robot's attack
     */
    public static final int DEFENCE_DELAY = 500;

    /**
     * Delay (in ms) used to simulate the time taken to repair a part of a robot
     */
    public static final int REPAIR_DELAY = 10;

    /** 
     * capacity of the repair line
     */
    public static final int MAX_REPAIRLINE_SIZE = 3;

    /** 
     * capacity of the bench
     */
    public static final int MAX_BENCH_SIZE = 3;

    /** 
     * delay for the operations of battle managers, mechanics
     */
    public static final long OPERATION_DELAY = 100;
}
