/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.robots.parts;

/**
 * A concrete implementation of a RobotPart; more specifically a Motor
 * part (a Wheel).
 * The only purpose of the concrete implementation is to define the
 * power of this part (this attribute characterises a motor) and 
 * its base energy coefficient.
 * 
 * @author marcoortolani
 */
public class Wheel extends AbstractRobotPart implements Motor {
    private static final double WHEEL_POWER = 3.0;
    private static final double WHEEL_BASE_ENERGY_COEFF = 0.10;

    /**
     * Constructor for a Wheel, which calls the superclass constructor
     * with the base energy coefficient for this part.
     */
    public Wheel() {
        super(WHEEL_BASE_ENERGY_COEFF);
    }

    /**
     * Returns the power of this part.
     * 
     * @return the power of this part
     */
    @Override
    public double getPower() {
        return WHEEL_POWER;
    }

    /**
     * Returns a string representation of this part.
     * 
     * @return a string representation with the id of this part
     */
    @Override
    public String toString() {
        return "wheel-" + getId();
    }
}
