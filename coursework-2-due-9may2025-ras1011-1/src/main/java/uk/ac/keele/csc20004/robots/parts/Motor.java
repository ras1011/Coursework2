/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.robots.parts;

/**
 * Interface defining the main methods for a generic Motor part,
 * i.e., the power.
 * 
 * @author marcoortolani
 */
public interface Motor extends RobotPart {
    /**
     * Returns the power of the motor.
     * This is not used in the current version of the coursework,
     * but it is provided for future extensions.
     * 
     * @return the power of the motor.
     */
    public double getPower();
}
