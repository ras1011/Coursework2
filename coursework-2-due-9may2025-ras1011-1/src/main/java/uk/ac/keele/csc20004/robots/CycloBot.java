/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/

package uk.ac.keele.csc20004.robots;

import uk.ac.keele.csc20004.robots.parts.Actuator;
import uk.ac.keele.csc20004.robots.parts.Blaster;
import uk.ac.keele.csc20004.robots.parts.Camera;
import uk.ac.keele.csc20004.robots.parts.Motor;
import uk.ac.keele.csc20004.robots.parts.Sensor;
import uk.ac.keele.csc20004.robots.parts.Torso;
import uk.ac.keele.csc20004.robots.parts.Wheel;

/**
 * A concrete implementation of a Robot.
 * This class makes use of the hierarchy of types for the robot parts
 * to create a particular type of robot, as defined in the coursework text.
 * 
 * @author marcoortolani
 */
public class CycloBot extends AbstractRobot {

    /**
     * A CycloBot is defined by its parts: 1 Wheel as Motor, a Torso as Frame
     * a Camera as Sensor and 1 Blaster and as Actuator.
     * 
     * @param name a string to identify this robot
     */
    public CycloBot(String name) {
        super(name,
            new Torso(),
            new Motor[]{new Wheel()},
            new Sensor[]{new Camera()},
            new Actuator[]{new Blaster()});
    }

    @Override
    public String toString() {
        return String.format("%10s", "CycloBot ") + super.toString();
    }
}
