/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.sample;

import java.util.LinkedList;

import uk.ac.keele.csc20004.RepairLine;
import uk.ac.keele.csc20004.robots.Robot;

/**
 * An example of implementation of a RepairLine.
 * Please note that this is provided as an example and it DOES NOT
 * necessarily meet the requirements of the coursework.
 * You should implement your own version of the RepairLine but you
 * should still implement the RepairLine interface.
 * 
 * @author marcoortolani
 */
public class SampleRepairLine implements RepairLine{
    private LinkedList<Robot> queue = new LinkedList<>();

    /**
     * Adds a robot to the repair line.
     * 
     * @param robot the robot to add to the repair line
     */
    public void addRobot(Robot robot) {
        queue.add(robot);
    }

    /**
     * Returns the next robot in the repair line, and removes it from the line.
     * Please note that you will have to provide the implementation for this method
     * matching the semantics of "next" according to the coursework specification.
     * 
     * @return the next robot in the repair line
     */
    public Robot getNextRobot() {
        return queue.poll();
    }

    /**
     * Prints out the robots in the repair line.
     */
    @Override
    public void printOut() {
        for (Robot robot : queue) {
            System.out.println(robot);
        }
    }

    /**
     * Returns the number of robots in the repair line.
     * 
     * @return the number of robots in the repair line
     */
    @Override
    public int size() {
        return queue.size();
    }
}
