/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.robots;

import uk.ac.keele.csc20004.robots.parts.Actuator;
import uk.ac.keele.csc20004.robots.parts.Blaster;
import uk.ac.keele.csc20004.robots.parts.Camera;
import uk.ac.keele.csc20004.robots.parts.Chassis;
import uk.ac.keele.csc20004.robots.parts.Motor;
import uk.ac.keele.csc20004.robots.parts.Sensor;
import uk.ac.keele.csc20004.robots.parts.Wheel;

/**
 * A concrete implementation of a Robot.
 * This class makes use of the hierarchy of types for the robot parts
 * to create a particular type of robot, as defined in the coursework text.
 * 
 * @author marcoortolani
 */
public class WheeledRobot extends AbstractRobot {

    /**
     * A WheeledRobot is defined by its parts: 4 wheels as Motor, a Chassis as Frame
     * and a Camera as Actuator.
     * 
     * @param name a string to identify this robot
     * 
     */
    public WheeledRobot(String name) {
        super(name,
              new Chassis(),
              new Motor[]{new Wheel(), new Wheel(), new Wheel(), new Wheel()},
              new Sensor[]{new Camera()},
              new Actuator[]{new Blaster()});
    }

    @Override
    public String toString() {
        return String.format("%10s", "Wheeled ") + super.toString();
    }
}
