/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/

package uk.ac.keele.csc20004.robots;

import uk.ac.keele.csc20004.robots.parts.Actuator;
import uk.ac.keele.csc20004.robots.parts.Arm;
import uk.ac.keele.csc20004.robots.parts.Camera;
import uk.ac.keele.csc20004.robots.parts.Leg;
import uk.ac.keele.csc20004.robots.parts.Motor;
import uk.ac.keele.csc20004.robots.parts.Sensor;
import uk.ac.keele.csc20004.robots.parts.Torso;

/**
 * A concrete implementation of a Robot.
 * This class makes use of the hierarchy of types for the robot parts
 * to create a particular type of robot, as defined in the coursework text.
 * 
 * @author marcoortolani
 */
public class HumanoidRobot extends AbstractRobot {

    /**
     * A HumanoidRobot is defined by its parts: 2 Legs as Motor, a Torso as Frame
     * a Camera as Sensor and 2 Arms and as Actuator.
     * 
     * @param name a string to identify this robot
     */
    public HumanoidRobot(String name) {
        super(name,
            new Torso(),
            new Motor[]{new Leg(), new Leg()},
            new Sensor[]{new Camera()},
            new Actuator[]{new Arm(), new Arm()});
    }

    @Override
    public String toString() {
        return String.format("%10s", "Humanoid ") + super.toString();
    }
}
