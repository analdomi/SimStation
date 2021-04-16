package simstation;

import mvc.*;

public abstract class Agent extends Bean implements Runnable{
    //public String name;
    public Heading heading;
    public int xc;
    public int yc;
    public boolean suspended;
    public boolean stopped;
    public Thread myThread;
    private Simulation world;

    public Agent(){
        suspended = false;
        stopped = false;
        myThread = null;
        xc = 100;
        yc = 100;
    }

    public void run() {
        myThread = Thread.currentThread();
        while(!stopped){
            try{
                update();
                Thread.sleep(20);
                checkSuspended();
            } catch (InterruptedException e){
                System.out.println(e);
            }
        }
    }

    private synchronized void checkSuspended() {
        try {
            while(!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public synchronized void suspend() {
        suspended = true;
        update();
    }

    public synchronized void resume() {
        suspended = false;
        notify();
    }

    public synchronized void stop() {
        stopped = true;
        update();
   }

    public int distance(Agent a){
        int distance = 0;

        return distance;
    }

    public void setWorld(Simulation s){
        world = s;
    }

    public void move(int steps) {
        if(heading == Heading.NORTH){
            yc -= steps;
        }
        else if(heading == Heading.SOUTH){
            yc += steps;
        }
        else if(heading == Heading.EAST){
            xc += steps;
        }
        else if(heading == Heading.WEST){
            xc -= steps;
        }
        world.changed();
    }

    public abstract void update();
}
