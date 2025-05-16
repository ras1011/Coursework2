/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.robots.parts;

/**
 * A concrete implementation of a RobotPart; more specifically an Actuator.
 * The only purpose of the concrete implementation is to define
 * the attack coefficient for this part. 
 * 
 * @author marcoortolani
 */
public class Blaster extends AbstractRobotPart implements Actuator {
    private static final double BLASTER_BASE_ENERGY_COEFF = 0.10;
    private static final double BLASTER_ATTACK_COEFF = 0.50;

    /**
     * Constructor for a Blaster, which calls the superclass constructor
     * with the base energy coefficient for this part.
     */
    public Blaster() {
        super(BLASTER_BASE_ENERGY_COEFF);
    }

    /**
     * Returns the attack coefficient for this part.
     * 
     * @return the attack coefficient for this part
     */
    @Override
    public double getAttackCoefficient() {
        return BLASTER_ATTACK_COEFF;
    }

    /**
     * Returns a string representation of this part.
     * 
     * @return a string representation with the id of this part
     */
    @Override
    public String toString() {
        return "blaster-" + getId();
    }
}
