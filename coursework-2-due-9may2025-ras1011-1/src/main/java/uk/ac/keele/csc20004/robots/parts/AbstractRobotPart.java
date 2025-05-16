/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.robots.parts;

import uk.ac.keele.csc20004.coursework2.SimulationParameters;

/**
 * Abstract class that implements the RobotPart interface.
 * It provides a basic implementation of the methods that are common to all the parts.
 * In particular, it provides a unique incremental id for each part and
 * manages the energy levels of the part.
 * 
 * @author marcoortolani
 */
public class AbstractRobotPart implements RobotPart {
    protected static int part_id = 0;
    protected final double energy_scale_coeff;

    private int id;
    protected double energy;

    /**
     * Constructor for an abstract RobotPart.
     * It requires an energy scaling coefficient that will be used to calculate the initial energy level.
     * The initial energy level also affects the time it takes to repair the part.
     * 
     * @param energy_scale_coeff the coefficient used to scale the base energy level
     */
    public AbstractRobotPart(double energy_scale_coeff) {
        this.energy_scale_coeff = energy_scale_coeff;
        refillEnergy();

        id = ++part_id;
    }

    /**
     * Returns the unique id of this part.
     * 
     * @return the id of this part
     */
    protected int getId() {
        return id;
    }

    /**
     * Returns the current energy level of this part.
     * This value is always between 0 and the initial energy level.
     * 
     * @return the current energy level of this part
     */
    @Override
    public double getCurrentEnergy() {
        return this.energy;
    }

    /**
     * Returns the initial energy level of this part, calculated as the base energy coefficient
     * multiplied by the standard base energy level.
     * 
     * @return the initial energy level of this part
     */
    @Override
    public double getInitialEnergy() {
        return SimulationParameters.BASE_ENERGY * energy_scale_coeff;
    }

    /**
     * Updates the energy level of this part by adding the given delta.
     * The delta can be positive or negative, but the energy level cannot be negative.
     * If the energy level exceeds the initial energy level, it is set to the initial energy level.
     * 
     * @param delta the amount to be added to the energy level
     */
    @Override
    public void updateEnergy(double delta) {
        this.energy += delta;
        if (this.energy < 0) {
            this.energy = 0;
        }
        if (this.energy > getInitialEnergy()) {
            this.energy = getInitialEnergy();
        }
    }

    /**
     * Restores the energy level of this part to the initial energy level.
     * 
     */
    @Override
    public void refillEnergy() {
        this.energy = getInitialEnergy();
    }
}
