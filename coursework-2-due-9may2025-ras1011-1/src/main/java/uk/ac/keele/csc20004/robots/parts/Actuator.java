/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.robots.parts;

/**
 * Interface defining the main methods for a generic Actuator part,
 * i.e., the attack coefficient.
 * 
 * @author marcoortolani
 */
public interface Actuator extends RobotPart {
    /**
     * Returns the attack coefficient of the actuator.
     * This is used to calculate the damage that the robot can inflict to another robot.
     * 
     * @return the attack coefficient of the actuator.
     */
    public double getAttackCoefficient();
}
