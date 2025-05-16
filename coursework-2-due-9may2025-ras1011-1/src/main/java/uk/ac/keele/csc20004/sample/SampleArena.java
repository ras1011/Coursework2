/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.sample;

import uk.ac.keele.csc20004.Bench;
import uk.ac.keele.csc20004.CombatPair;
import uk.ac.keele.csc20004.RepairLine;
import uk.ac.keele.csc20004.RobotArena;
import uk.ac.keele.csc20004.coursework2.SimulationParameters;
import uk.ac.keele.csc20004.robots.CycloBot;
import uk.ac.keele.csc20004.robots.HumanoidRobot;
import uk.ac.keele.csc20004.robots.Robot;
import uk.ac.keele.csc20004.robots.WheeledRobot;

/**
 * An example of implementation of a RobotArena.
 * Please note that this is provided as an example and it DOES NOT
 * necessarily meet the requirements of the coursework.
 * You should implement your own version of the RobotArena.
 * Feel free to extend the abstract class RobotArena and implement
 * the methods as you see fit.
 * 
 * @author marcoortolani
 */
public class SampleArena extends RobotArena {
    private static enum RobotType {
        HUMANOID,
        WHEELED,
        CYCLOBOT;
    }
    /**
     * The constructor for the SampleArena.
     * 
     * @param studentId your student ID
     * @param capacity the capacity of the arena
     * @param repairLine the repair line (needs to be created separately)
     * @param bench the bench (needs to be created separately)
     */
    public SampleArena(int studentId, int capacity, RepairLine repairLine, Bench bench) {
        super(studentId, capacity, repairLine, bench);

        RobotType[] values = RobotType.values();
        for (int i = 0; i < capacity; i++) {
            RobotType t = values[i % values.length];
            Robot r = createRobot(i, t);
            addRobot(r);
        }
    }

    /**
     * This method simulates the repair of a robot after it is
     * extracted from the repair line.
     * Only the components with 0 energy are repaired by using the
     * appropriate bay.
     * You can implement your own version of this method in your
     * implementation of the RobotArena.
     * 
     * @param r the robot to repair
     */
    @Override
    public void repair(Robot r) {
        if (r.getFrameEnergy() == 0.0) {
            r.refillFrameEnergy(frameBay);
        }
        if (r.getMotorEnergy() == 0.0) {
            r.refillMotorEnergy(motorBay);
        }
        if (r.getSensorsEnergy() == 0.0) {
            r.refillSensorsEnergy(sensorBay);
        }
        if (r.getActuatorsEnergy() == 0.0) {
            r.refillActuatorsEnergy(actuatorBay);
        }
    }

    /**
     * Helper method to create a robot with the given ID and type.
     * In the current implementation, the type is used to create either a humanoid or a wheeled robot.
     * Feel free to add more types of robots in your implementation.
     * 
     * @param robotId the ID of the robot
     * @param type the type of robot to create
     * @return the robot created
     */
    private Robot createRobot(int robotId, RobotType type) {
        switch (type) {
            case HUMANOID:
                return new HumanoidRobot("R-" +robotId);
            case WHEELED:
                return new WheeledRobot("R-" + robotId);
            case CYCLOBOT:
            default:
                return new CycloBot("R-" + robotId);
        }
    }

    /**
     * Returns the winner of the arena.
     * In this implementation, this method iterates over all the robots in the arena
     * and returns the one with the highest energy level.
     * 
     * @return the robot with the highest energy level from the arena
     */
    @Override
    public Robot getWinner() {
        Robot winner = null;

        double maxEnergy = 0.0;
        for (Robot r : robots) {
            double energy = r.getFrameEnergy() + r.getMotorEnergy() + r.getSensorsEnergy() + r.getActuatorsEnergy();
            if (energy > maxEnergy) {
                maxEnergy = energy;
                winner = r;
            }
        }

        return winner;
    }

    /**
     * Main method to run the simulation in the sample scenario.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bench bench = new SampleBench();
        RepairLine repairLine = new SampleRepairLine();
        SampleArena arena = new SampleArena(1234567, SimulationParameters.NUM_ROBOTS, repairLine, bench);

        System.out.println("Simulation begins...");
        System.out.println("Base strike intensity: " + SimulationParameters.STRIKE_INTENSITY);
        arena.printStats();

        int rounds = 30;

        do { 
            // Select a random integer between 0 and size of arena/2
            // This will be the number of fights (since each fight involves 2 robots
            // it makes sense to involve at most half the number of robots in the arena)
            int numFights = (int) (Math.random() * (arena.getNumRobots() / 2));

            System.out.println();
            System.out.println("Simulating " + numFights + " fights");
            System.out.println();

            for (int i = 0; i < numFights; i++) {
                CombatPair p = arena.getOpponents();
                System.out.println("Fight #" + (i+1) + ":");
                System.out.println(String.format("%10s", "before: ") + p);
                p.fight();
                System.out.println(String.format("%10s", "after: ") + p);

                double energy1 = p.getRobot1().getFrameEnergy() + 
                    p.getRobot1().getMotorEnergy() + 
                    p.getRobot1().getSensorsEnergy() + 
                    p.getRobot1().getActuatorsEnergy();

                if (energy1 > 0) {
                    arena.addRobot(p.getRobot1());
                } else {
                    repairLine.addRobot(p.getRobot1());
                }

                double energy2 = p.getRobot2().getFrameEnergy() + 
                    p.getRobot2().getMotorEnergy() + 
                    p.getRobot2().getSensorsEnergy() + 
                    p.getRobot2().getActuatorsEnergy();

                if (energy2 > 0) {
                    arena.addRobot(p.getRobot2());
                } else {
                    repairLine.addRobot(p.getRobot2());
                }
            }
            System.out.println();
            arena.printStats();
            System.out.println();

            // Select a random integer between 0 and size of repair line
            // This will be the number of robots to repair
            int numRepairs = (int) (Math.random() * repairLine.size());

            for (int i = 0; i < numRepairs; i++) {
                Robot r = repairLine.getNextRobot();

                arena.repair(r);

                bench.addRobot(r);
            }

            // Select a random integer between 0 and size of bench
            // This will be the number of robots to add back to the arena
            int numBench = (int) (Math.random() * bench.size());
            for (int i = 0; i < numBench; i++) {
                Robot r = bench.getNextRobot();
                arena.addRobot(r);
            }
        } while (--rounds > 0);

        System.out.println("Simulation ends.");
        // get the robot with the highest energy level from the arena

        Robot winner = arena.getWinner();
        if (winner != null) {
            System.out.println("The winner is: " + winner);
        } else {
            System.out.println("No winner found");
        }
    }
}
