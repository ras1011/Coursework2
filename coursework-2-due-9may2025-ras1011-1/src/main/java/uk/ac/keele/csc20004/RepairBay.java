/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004;

import uk.ac.keele.csc20004.coursework2.SimulationParameters;

/**
 * A simple class to represent a repair bay.
 * It provides a method to simulate the repair of a robot, which 
 * requires a certain amount of time.
 * 
 * @author marcoortolani
 */
public class RepairBay {
    private final String name;

    /**
     * Creates a new RepairBay with the given name (e.g., "Frame").
     * 
     * @param name the name of the repair bay
     */
    public RepairBay(String name) {
        this.name = name;
    }

    /**
     * Simulates the repair of a robot, which requires a certain amount of time
     * (this will be proportional to the initial energy of the part)
     * 
     * @param energy the energy level to refill (time required to repair the robot)
     */
    public void operate(double energy) {
        try {
            long repairTime = Math.round(energy * SimulationParameters.REPAIR_DELAY);
            Thread.sleep(repairTime);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
        
    }

    @Override
    public String toString() {
        return String.format("%15s", name + " bay");
    }
}
