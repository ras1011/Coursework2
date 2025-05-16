package uk.ac.keele.csc20004.robots.parts;

/**
 * Interface defining the main methods for a generic Robot part,
 * i.e., the ones to manage its energy.
 * 
 * @author marcoortolani
 */
public interface RobotPart {
    /**
     * Returns the current energy level of this part.
     * This value is always between 0 and the initial energy level.
     * 
     * @return the current energy level of this part
     */
    public double getCurrentEnergy();

    /**
     * Returns the initial energy level of this part, calculated as the base energy coefficient
     * multiplied by the standard base energy level.
     * 
     * @return the initial energy level of this part
     */
    public double getInitialEnergy();

    /**
     * Updates the energy level of this part by adding the given delta.
     * The delta can be positive or negative, but the energy level cannot be negative.
     * If the energy level exceeds the initial energy level, it is set to the initial energy level.
     * 
     * @param delta the amount to be added to the energy level
     */
    public void updateEnergy(double delta);

    /**
     * Restores the energy level of this part to the initial energy level.
     * 
     */
    public void refillEnergy();
}
