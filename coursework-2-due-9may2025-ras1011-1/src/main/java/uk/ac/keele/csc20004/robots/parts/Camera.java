/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.robots.parts;

/**
 * A concrete implementation of a RobotPart; more specifically a Sensor.
 * The only purpose of the concrete implementation is to define
 * the defence coefficient for this part.
 * 
 * @author marcoortolani
 */
public class Camera extends AbstractRobotPart implements Sensor {
    private static final double CAMERA_BASE_ENERGY_COEFF = 0.25;
    private static final double CAMERA_DEFENCE_COEFF = 0.10;

    /**
     * Constructor for a Camera, which calls the superclass constructor
     * with the base energy coefficient for this part.
     */
    public Camera() {
        super(CAMERA_BASE_ENERGY_COEFF);
    }

    /**
     * Returns the defence coefficient for this part.
     * 
     * @return the defence coefficient for this part
     */
    @Override
    public double getDefenceCoefficient() {
        return CAMERA_DEFENCE_COEFF;
    }

    /**
     * Returns a string representation of this part.
     * 
     * @return a string representation with the id of this part
     */
    @Override
    public String toString() {
        return "camera-" + super.getId();
    }
}
