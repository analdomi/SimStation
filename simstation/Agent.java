package simstation;

import mvc.*;

public abstract class Agent extends Bean implements Runnable{
    public String name;
    public Heading heading;
    public int xc;
    public int yc;
    public boolean suspended;
    public boolean stopped;
    public Thread myThread;

    public void run() {
        update();
    }

    public synchronized void start() {
    }

    public synchronized void suspend() {
        suspended = true;
    }

    public synchronized void resume() {
        suspended = false;
    }

    public synchronized void stop() {
        stopped = true;
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
