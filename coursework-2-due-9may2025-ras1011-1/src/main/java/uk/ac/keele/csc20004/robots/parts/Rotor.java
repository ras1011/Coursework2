/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.robots.parts;

/**
 * A concrete implementation of a RobotPart; more specifically a Motor
 * part (a Rotor).
 * The only purpose of the concrete implementation is to define the
 * power of this part (this attribute characterises a motor) and 
 * its base energy coefficient.
 * 
 * @author marcoortolani
 */
public class Rotor extends AbstractRobotPart implements Motor {
    private static final double ROTOR_POWER = 9.5;
    private static final double ROTOR_BASE_ENERGY_COEFF = 0.25;

    /**
     * Constructor for a Rotor, which calls the superclass constructor
     * with the base energy coefficient for this part.
     */
    public Rotor() {
        super(ROTOR_BASE_ENERGY_COEFF);
    }

    /**
     * Returns the power of this part.
     * 
     * @return the power of this part
     */
    @Override
    public double getPower() {
        return ROTOR_POWER;
    }

    /**
     * Returns a string representation of this part.
     * 
     * @return a string representation with the id of this part
     */
    @Override
    public String toString() {
        return "rotor-" + getId();
    }
}
