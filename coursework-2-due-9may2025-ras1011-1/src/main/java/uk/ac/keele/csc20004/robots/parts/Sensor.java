/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.robots.parts;

/**
 * Interface defining the main method for a generic Sensor part,
 * i.e., managing the defence coefficient.
 * 
 * @author marcoortolani
 */
public interface Sensor extends RobotPart {

    /**
     * Returns the defence coefficient for this part.
     * 
     * @return the defence coefficient for this part
     */
    public double getDefenceCoefficient();
}
