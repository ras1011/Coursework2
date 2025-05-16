/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004;

import java.util.Random;

import uk.ac.keele.csc20004.coursework2.SimulationParameters;
import uk.ac.keele.csc20004.robots.Robot;

/**
 * A simple class to store the pair of robots selected for combat.
 * It also provides a method to simulate the battle.
 * One of the robots will attack the other, and then the other will attack back.
 * *
 * 
 * @author marcoortolani
 */
public class CombatPair {
    private Random random = new Random();
    private final Robot robot1;
    private final Robot robot2;

    /**
     * Creates a new CombatPair with the two robots that will fight.
     * 
     * @param robot1 the first robot (will attck first)
     * @param robot2 the other robot (will attack second)
     */
    public CombatPair(Robot robot1, Robot robot2) {
        this.robot1 = robot1;
        this.robot2 = robot2;
    }

    /**
     * Returns the first robot.
     * 
     * @return the first robot.
     */
    public Robot getRobot1() {
        return robot1;
    }

    /**
     * Returns the second robot.
     * 
     * @return the second robot.
     */
    public Robot getRobot2() {
        return robot2;
    }

    /**
     * Simulates the battle between the two robots.
     * The first robot will attack the second, and then the second will attack back.
     */
    public void fight() {
        try {
            long delay = (long) (random.nextDouble() * SimulationParameters.OPERATION_DELAY);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot1.attack(robot2);
        robot2.attack(robot1);
    }

    @Override
    public String toString() {
        return "CombatPair {" + robot1 + " - " + robot2 + "}";
    }
}