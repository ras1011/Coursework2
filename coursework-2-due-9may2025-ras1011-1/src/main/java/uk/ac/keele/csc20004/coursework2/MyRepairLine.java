package uk.ac.keele.csc20004.coursework2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import uk.ac.keele.csc20004.RepairLine;
import uk.ac.keele.csc20004.robots.Robot;

/**
 * @author 23046671
 */

public class MyRepairLine implements RepairLine {
    private final BlockingQueue<Robot> queue;

    /**
     * create new repair line with max size
     */

     public MyRepairLine() {
        this.queue = new LinkedBlockingQueue<>(SimulationParameters.MAX_REPAIRLINE_SIZE);
    }

   /** 
    *  adds robot to the queue and block any other robot
    */

    @Override
    public void addRobot(Robot r) {
        try {
            queue.put(r);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Robot getNextRobot() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

     /**
      * print which robots are in queue
      */
    @Override
    public void printOut() {
        System.out.println("Robots in Repair Line:");
        for (Robot r : queue) {
            System.out.println(r);
        }
    }

    /**
     *  return amount of robots in repair line.
     */
    public int size() {
        return queue.size();
    }
}