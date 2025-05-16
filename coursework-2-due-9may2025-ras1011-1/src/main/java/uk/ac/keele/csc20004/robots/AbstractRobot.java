/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/

package uk.ac.keele.csc20004.robots;

import uk.ac.keele.csc20004.RepairBay;
import uk.ac.keele.csc20004.coursework2.SimulationParameters;
import uk.ac.keele.csc20004.robots.parts.Actuator;
import uk.ac.keele.csc20004.robots.parts.Frame;
import uk.ac.keele.csc20004.robots.parts.Motor;
import uk.ac.keele.csc20004.robots.parts.Sensor;

/**
 * Abstract class defining the main methods for a generic Robot. Note that you
 * cannot directly create instances of this class: it's abstract, and it 
 * does not define the parts it's composed of. Rather, you
 * should use the appropriate derived classes or create your own.
 * 
 * @author marcoortolani
 */
public abstract class AbstractRobot implements Robot {
    protected final String name;

    protected final Frame frame;
    protected final Motor[] motorParts;
    protected final Sensor[] sensorParts;
    protected final Actuator[] actuatorParts;
    protected final int numParts;

    protected final double defenceCoeff;
    protected final double attackCoeff;

    /**
     * The constructor allows to create robots with any combination of parts;
     * however, there must always be a Motor, a Frame and an Actuator. The idea is
     * that constructors of derived classes will take care of creating robots
     * according to specifications.
     * 
     * @param name          a mnemonic name for identifying this robot
     * @param frame         a part of type Frame
     * @param motorParts    an array of parts for the Motor section of the robot
     * @param sensorParts   an array of parts for the Sensor section of the robot
     * @param actuatorParts an array of parts for the Actuator section of the robot
     */
    protected AbstractRobot(String name, Frame frame, Motor[] motorParts, 
                            Sensor[] sensorParts, Actuator[] actuatorParts) {
        this.name = name;

        this.motorParts = motorParts;
        this.frame = frame;
        this.sensorParts = sensorParts;
        this.actuatorParts = actuatorParts;

        numParts = motorParts.length + sensorParts.length + actuatorParts.length + 1;

        double ar = 0.0;
        for (Actuator a : actuatorParts) {
            ar += a.getAttackCoefficient();
        }
        // set the attack coefficient to the sum of the attack coefficients of the actuators
        // but it cannot be greater than 1.0
        attackCoeff = ar <= 1.0 ? ar : 1.0;

        double dr = 0.0;
        for (Sensor s : sensorParts) {
            dr += s.getDefenceCoefficient();
        }
        // set the defence coefficient to the sum of the defence coefficients of the sensors
        // but it cannot be greater than 1.0
        defenceCoeff = dr <= 1.0 ? dr : 1.0;
    }

    /**
     * Returns the number of parts of this robot
     * 
     * @return the number of parts of this robot
     */
    @Override
    public int getNumParts() {
        return numParts;
    }   

    /**
     * This methods "repairs" the frame by refilling its energy levels.
     * If the frame energy is not zero, it does nothing.
     * It requires a RepairBay object to operate.
     * 
     * @param bay the RepairBay object that is used to refill the energy levels
     */
    public void refillFrameEnergy(RepairBay bay){
        if (frame.getCurrentEnergy() == 0.0) {
            // simulate repair time
            bay.operate(frame.getInitialEnergy());
            frame.refillEnergy();
        }
    }

    /**
     * This methods "repairs" the motor parts by refilling their energy levels.
     * If the motor energy is not zero, it does nothing.
     * It requires a RepairBay object to operate.
     * 
     * @param bay the RepairBay object that is used to refill the energy levels
     */
    public void refillMotorEnergy(RepairBay bay){
        for (Motor m : motorParts) {
            if (m.getCurrentEnergy() == 0.0) {
                // simulate repair time
                bay.operate(m.getInitialEnergy());
                m.refillEnergy();
            }
        }
    }

    /**
     * This methods "repairs" the sensor parts by refilling their energy levels.
     * If the sensor energy is not zero, it does nothing.
     * It requires a RepairBay object to operate.
     * 
     * @param bay the RepairBay object that is used to refill the energy levels
     */
    public void refillSensorsEnergy(RepairBay bay){
        for (Sensor s : sensorParts) {
            if (s.getCurrentEnergy() == 0.0) {
                // simulate repair time
                bay.operate(s.getInitialEnergy());
                s.refillEnergy();
            }
        }
    }

    /**
     * This methods "repairs" the actuator parts by refilling their energy levels.
     * If the actuator energy is not zero, it does nothing.
     * It requires a RepairBay object to operate.
     * 
     * @param bay the RepairBay object that is used to refill the energy levels
     */
    public void refillActuatorsEnergy(RepairBay bay){
        for (Actuator a : actuatorParts) {
            if (a.getCurrentEnergy() == 0.0) {
                // simulate repair time
                bay.operate(a.getInitialEnergy());
                a.refillEnergy();
            }
        }
    }

    /**
     * Returns the energy level of the motor parts of this robot
     * 
     * @return the energy level of the motor parts
     */
    public double getMotorEnergy() {
        double motorEnergy = 0.0;
        for (Motor m : motorParts) {
            motorEnergy += m.getCurrentEnergy();
        }
        return motorEnergy;
    }

    /**
     * Returns the energy level of the frame of this robot
     * 
     * @return the energy level of the frame
     */
    public double getFrameEnergy() {
        return frame.getCurrentEnergy();
    }

    /**
     * Returns the energy level of the sensor parts of this robot
     * 
     * @return the energy level of the sensor parts
     */
    public double getSensorsEnergy() {
        double sensorsEnergy = 0.0;
        for (Sensor s : sensorParts) {
            sensorsEnergy += s.getCurrentEnergy();
        }
        return sensorsEnergy;
    }

    /**
     * Returns the energy level of the actuator parts of this robot
     * 
     * @return the energy level of the actuator parts
     */
    public double getActuatorsEnergy() {
        double actuatorEnergy = 0.0;
        for (Actuator a : actuatorParts) {
            actuatorEnergy += a.getCurrentEnergy();
        }
        return actuatorEnergy;
    }

    @Override
    public double getDefenceceCoeff() {
        return defenceCoeff;
    }

    @Override
    public double getAttackCoeff() {
        return attackCoeff;
    }

    /**
     * If any of the energy levels is zero, the robot cannot attack.
     * The attack is implemented in practice by calling the fendOff() 
     * method of the attacked robot (which decreases its energy levels as appropriate)
     * 
     * @param r the reference to the Robot object to be attacked
     */
    public void attack(Robot r) {
        r.fendOff(this.attackCoeff);
    }

    /**
     * This method implements the response to an attack.
     * It basically causes a delay (simulating the time needed for the attack)
     * and decreases the energy level of this robot's parts as appropriate.
     * 
     * @param strikeCoefficient the strength of the strike (i.e. the attack coefficient of the attacker)
     * 
     * @return the energy level of the robot after the attack
     */
    public double fendOff(double strikeCoefficient) {
        try {
            Thread.sleep(SimulationParameters.DEFENCE_DELAY);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }

        double energyUpdateCoeff = strikeCoefficient - defenceCoeff;
        if (energyUpdateCoeff < 0.0) {
            energyUpdateCoeff = 0.0;
        }

        double finalEnergy = 0.0;

        double damage = energyUpdateCoeff * SimulationParameters.STRIKE_INTENSITY;
        System.out.println(name + " is hit with strike coeff " + strikeCoefficient + " - damage: " + damage);

        frame.updateEnergy(-damage);
        finalEnergy += frame.getCurrentEnergy();

        for (Motor m : motorParts) {
            m.updateEnergy(-damage);
            finalEnergy += m.getCurrentEnergy();
        }

        for (Sensor s : sensorParts) {
            s.updateEnergy(-damage);
            finalEnergy += s.getCurrentEnergy();
        }

        for (Actuator a : actuatorParts) {
            a.updateEnergy(-damage);
            finalEnergy += a.getCurrentEnergy();
        }

        return finalEnergy;
    }

    /**
     * Returns a a string describing the composing parts of this robot
     * 
     * @return a string describing the composing parts of this robot
     */
    public String getPartsDescriptions() {
        String description = "[ ";

        for (Motor m : motorParts) {
            description += m + " ";
        }

        description += "on " + frame + "; with: ";

        for (Sensor s : sensorParts) {
            description += s + " ";
        }

        description += "and ";

        for (Actuator a : actuatorParts) {
            description += a + " ";
        }

        description += "]";

        return description;
    }

    /**
     * Simply returns the name of this robot
     * @return the name of this robot
     */
    public String getName() {
        return name;
    }

    /**
     * A (short) descriptive string for this robot. 
     * It contains the name and the overall energy of the robot.
     * 
     * @return the short description of this robot
     */
    @Override
    public String toString() {
        double energy = 0.0;
        energy += frame.getCurrentEnergy();
        for (Motor m : motorParts) {
            energy += m.getCurrentEnergy();
        }
        for (Sensor s : sensorParts) {
            energy += s.getCurrentEnergy();
        }
        for (Actuator a : actuatorParts) {
            energy += a.getCurrentEnergy();
        }

        return String.format("%-5s", name) + " [" + numParts + " parts" + 
            "|e:" +  String.format("%6.2f", energy) + 
            "/d:" + String.format("%4.2f", defenceCoeff) + 
            "/a:" + String.format("%4.2f", attackCoeff) + "]";
    }
}
