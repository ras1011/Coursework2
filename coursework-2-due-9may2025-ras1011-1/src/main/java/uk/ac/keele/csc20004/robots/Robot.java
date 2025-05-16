/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/

package uk.ac.keele.csc20004.robots;

import uk.ac.keele.csc20004.RepairBay;

/**
 * Interface defining the main methods for a generic Robot. 
 * See the AbstractRobot class for an implementation of some of
 * the main methods.
 * 
 * @author marcoortolani
 */
public interface Robot {

    /**
     * Returns the number of parts of this robot
     * 
     * @return the number of parts of this robot
     */
    public int getNumParts();

    /**
     * Returns the energy level of the motor parts of this robot
     * 
     * @return the energy level of the motor parts
     */
    public double getMotorEnergy();

    /**
     * Returns the energy level of the frame of this robot
     * 
     * @return the energy level of the frame
     */
    public double getFrameEnergy();

    /**
     * Returns the energy level of the sensor parts of this robot, computed as the sum of the
     * energy levels of all its parts.
     * 
     * @return the energy level of the sensor parts
     */
    public double getSensorsEnergy();

    /**
     * Returns the energy level of the actuator parts of this robot, computed as the sum of the
     * energy levels of all its parts.
     * 
     * @return the energy level of the actuator parts
     */
    public double getActuatorsEnergy();

    /**
     * This methods "repairs" the frame by refilling its energy levels.
     * If the frame energy is not zero, it does nothing.
     * It requires a RepairBay object to operate.
     * 
     * @param bay the RepairBay object that is used to refill the energy levels
     */
    public void refillFrameEnergy(RepairBay bay);

    /**
     * This methods "repairs" the motor parts by refilling their energy levels.
     * If the motor energy is not zero, it does nothing.
     * It requires a RepairBay object to operate.
     * 
     * @param bay the RepairBay object that is used to refill the energy levels
     */
    public void refillMotorEnergy(RepairBay bay);

    /**
     * This methods "repairs" the sensor parts by refilling their energy levels.
     * If the sensor energy is not zero, it does nothing.
     * It requires a RepairBay object to operate.
     * 
     * @param bay the RepairBay object that is used to refill the energy levels
     */
    public void refillSensorsEnergy(RepairBay bay);

    /**
     * This methods "repairs" the actuator parts by refilling their energy levels.
     * If the actuator energy is not zero, it does nothing.
     * It requires a RepairBay object to operate.
     * 
      * @param bay the RepairBay object that is used to refill the energy levels
    */
    public void refillActuatorsEnergy(RepairBay bay);

    /**
     * Returns the defence coefficient of the robot, computed as the sum of the
     * defence coefficients of the sensor parts.
     * 
     * @return the defence coefficient of the robot
     */
    public double getDefenceceCoeff();

    /**
     * Returns the attack coefficient of the robot, computed as the sum of the
     * attack coefficients of the actuator parts.
     * 
     * @return the attack coefficient of the robot
     */
    public double getAttackCoeff();

    /**
     * If any of the energy levels is zero, the robot cannot attack.
     * The attack is implemented in practice by calling the fendOff() 
     * method of the attacked robot (which decreases its energy levels as appropriate)
     * 
     * @param r the reference to the Robot object to be attacked
     */
    public void attack(Robot r);

    /**
     * This method implements the response to an attack.
     * It basically causes a delay (simulating the time needed for the attack)
     * and decreases the energy level of this robot's parts as appropriate.
     * 
     * @param strikeCoefficient the strength of the strike (i.e. the attack coefficient of the attacker)
     * 
     * @return the energy level of the robot after the attack
     */
    public double fendOff(double strikeCoefficient);    
}
