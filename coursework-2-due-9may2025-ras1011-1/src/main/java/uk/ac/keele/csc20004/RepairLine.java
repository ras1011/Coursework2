/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004;

import uk.ac.keele.csc20004.robots.Robot;

/**
 * Interface for a RepairLine. 
 * The RepairLine is a queue of robots that need to be repaired.
 * You will have to provide your own implementation of this interface,
 * according to the requirements of the coursework.
 * 
 * @author marcoortolani
 */
public interface RepairLine {
    /**
     * Returns the number of robots in the repair line.
     * 
     * @return the number of robots in the repair line.
     */
    public int size();

    /**
     * Adds a robot to the repair line.
     * Check the requirements of the coursework to see what happens if the repair line is full.
     * 
     * @param robot the robot to add to the repair line.
     */
    public void addRobot(Robot robot);

    /**
     * Removes the next robot from the repair line.
     * Check the requirements of the coursework to determine what is the semantics of "next".
     * Check the requirements of the coursework to see what happens if the repair line is empty.
     * 
     * @return the next robot from the repair line.
     */
    public Robot getNextRobot();

    /**
     * Prints out the repair line.
     * The format of the printout is up to you, but it should be clear and easy to read.
     */
    public void printOut();
}
