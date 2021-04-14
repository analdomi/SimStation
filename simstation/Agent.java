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

    public synchronized void start() {
        state = AgentState.READY;
    }

    public synchronized void suspend() {
        state = AgentState.SUSPENDED;
    }

    public synchronized void resume() {
        state = AgentState.READY;
    }

    public synchronized void stop() {
        state = AgentState.STOPPED;
   }

    public int distance(Agent a){
        int distance = 0;

        return distance;
    }

    public void move(int steps) {
        if(heading.equals("NORTH")){
            yc -= steps;
        }
        else if(heading.equals("SOUTH")){
            yc += steps;
        }
        else if(heading.equals("EAST")){
            xc += steps;
        }
        else if(heading.equals("WEST")){
            xc -= steps;
        }
    }
    public abstract void update();
}
