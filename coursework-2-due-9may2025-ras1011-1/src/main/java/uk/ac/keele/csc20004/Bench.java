/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004;

import uk.ac.keele.csc20004.robots.Robot;

/**
 * Interface for a Bench.
 * The Bench is a queue of robots that wait to be placed back into the arena
 * after they have been repaired.
 * You will have to provide your own implementation of this interface,
 * according to the requirements of the coursework.
 * 
 * @author marcoortolani
 */
public interface Bench {

    /**
     * Returns the number of robots in the bench.
     * 
     * @return the number of robots in the bench.
     */
    public int size();

    /**
     * Adds a robot to the bench.
     * Check the requirements of the coursework to see what happens if the bench is full.
     * 
     * @param robot the robot to add to the bench.
     */
    public void addRobot(Robot robot);

    /**
     * Removes the next robot from the bench.
     * Check the requirements of the coursework to determine what is the semantics of "next".
     * Check the requirements of the coursework to see what happens if the bench is empty.
     * 
     * @return the next robot in the bench.
     */
    public Robot getNextRobot();

    /**
     * Prints out the bench.
     * The format of the printout is up to you, but it should be clear and easy to read.
     */
    public void printOut();
}
