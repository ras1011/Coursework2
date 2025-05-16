package uk.ac.keele.csc20004.coursework2;

import java.util.List;
import java.util.Random;

import uk.ac.keele.csc20004.Bench;
import uk.ac.keele.csc20004.RepairLine;
import uk.ac.keele.csc20004.robots.AbstractRobot;
import uk.ac.keele.csc20004.robots.Robot;
/**

 * @author 23046671
 */
public class BattleManager implements Runnable {
    private final int id;
    private final List<Robot> arena;
    private final RepairLine repairLine;
    private final Bench bench;
    private final Random random = new Random();

    /**
     *constructor for battlemanager
     */

    public BattleManager(int id, List<Robot> arena, RepairLine repairLine, Bench bench) {
        this.id = id;
        this.arena = arena;
        this.repairLine = repairLine;
        this.bench = bench;
    }

    /** 
     *  create a loop for the program
     * */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (random.nextDouble() * 100)); 

                Robot r1, r2;

                synchronized (arena) {
                    if (arena.size() < 2) continue;

                    //selecting robots for the fight

                    int idx1 = random.nextInt(arena.size());
                    int idx2;
                    do {
                        idx2 = random.nextInt(arena.size());
                    } while (idx2 == idx1);

                    r1 = arena.get(idx1);
                    r2 = arena.get(idx2);

                    System.out.println("(BattleManager " + id + ") FIGHT START: " +
                        ((AbstractRobot) r1).getName() + " vs " + ((AbstractRobot) r2).getName());

                    // robots fighting
                    r1.attack(r2);
                    r2.attack(r1);

                    for (Robot robot : new Robot[]{r1, r2}) {
                        if (isDamaged(robot)) {
                            arena.remove(robot);
                            repairLine.addRobot(robot);
                            System.out.println("(BattleManager " + id + ") Sent " +
                                ((AbstractRobot) robot).getName() + " to repair line");
                        }
                    }

                    // Attempt to refill arena
                    if (arena.size() < SimulationParameters.NUM_ROBOTS) {
                        Robot repaired = bench.getNextRobot();
                        if (repaired != null) {
                            arena.add(repaired);
                            System.out.println("(BattleManager " + id + ") Brought back " +
                                ((AbstractRobot) repaired).getName() + " from bench");
                        }
                    }
                }
                //delay to prevent locking

                Thread.sleep((long) (random.nextDouble() * 100));


                //catches any erros 

            } catch (InterruptedException e) {
                System.err.println("(BattleManager " + id + ") Interrupted");
            }
        }
    }

    /**
     * check for eneregy
     */ 
    private boolean isDamaged(Robot robot) {
        return robot.getFrameEnergy() == 0 ||
               robot.getMotorEnergy() == 0 ||
               robot.getSensorsEnergy() == 0 ||
               robot.getActuatorsEnergy() == 0;
    }
}
