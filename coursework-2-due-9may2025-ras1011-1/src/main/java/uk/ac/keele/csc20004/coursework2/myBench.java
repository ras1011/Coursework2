package uk.ac.keele.csc20004.coursework2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import uk.ac.keele.csc20004.Bench;
import uk.ac.keele.csc20004.robots.Robot;


/**
 * @author 23046671
 */

public class myBench implements Bench {
    private final BlockingQueue<Robot> bench;

    //create new bench with max size
    public myBench() {
        this.bench = new LinkedBlockingQueue<>(uk.ac.keele.csc20004.coursework2.SimulationParameters.MAX_BENCH_SIZE);
    }

    //add robot to bench and if its full block the next robot
    @Override
    public void addRobot(Robot r) {
        try {
            bench.put(r);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // remove next robot
    @Override
    public Robot getNextRobot() {
        try {
            return bench.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    // print out the robot which is on the bench
    @Override
    public void printOut() {
        System.out.println("Robots on Bench:");
        for (Robot r : bench) {
            System.out.println(r);
        }
    }

   // how many robots are on the bench currently
    public int size() {
        return bench.size();
    }
}