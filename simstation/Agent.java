package simstation;

import mvc.*;

public abstract class Agent extends Bean implements Runnable{
    private String name;
    private Heading heading;
    private int xc;
    private int yc;

    public void run() {
        update();
    }

    public void start() {

    }

    public void suspend() {

    }

    public void resume() {

    }

   public void stop() {

   }

    public void move(int steps) {

    }

    public abstract void update();
}
