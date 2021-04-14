package simstation;

import mvc.*;

public abstract class Agent extends Bean implements Runnable{
    public String name;
    public Heading heading;
    public int xc;
    public int yc;
    public AgentState state;

    public void run() {
        state = AgentState.RUNNING;
        update();
    }

    public void start() {
        state = AgentState.READY;
    }

    public void suspend() {
        state = AgentState.SUSPENDED;
    }

    public void resume() {
        state = AgentState.READY;
    }

   public void stop() {
        state = AgentState.STOPPED;
   }

    public void move(int steps) {

    }

    public abstract void update();
}
