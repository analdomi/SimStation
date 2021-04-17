package simstation;

import mvc.*;

public abstract class Agent extends Bean implements Runnable{

    public Heading heading;
    public int x, y;
    public boolean suspended, stopped;
    public Thread myThread;
    protected Simulation world;

    public Agent(){
        suspended = false;
        stopped = false;
        myThread = null;
        x = Utilities.rng.nextInt(world.SIZE);
        y = Utilities.rng.nextInt(world.SIZE);
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
                stopped = false;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public synchronized void suspend() {
        suspended = true;
    }

    public synchronized void resume() {
        stopped = false;
        notify();
    }

    public synchronized void stop() {
        stopped = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Heading getHeading(){
        return heading;
    }

    public int distance(Agent a){ //Need to finish this
        int distance = (int)(Math.sqrt((a.getY() - y) * (a.getY() - y) + (a.getX() - x) * (a.getX() - x)));
        return distance;
    }

    public void setWorld(Simulation s){
        world = s;
    }

    public void move(int steps) {
        if(heading == Heading.NORTH){
            y -= steps;
            if(y<=0){
                y += world.SIZE;
            }

        }
        else if(heading == Heading.SOUTH){
            y += steps;
            if(y>=world.SIZE){
                y -= world.SIZE;
            }
        }
        else if(heading == Heading.EAST){
            x += steps;
            if(x >= world.SIZE){
                x -= world.SIZE;
            }
        }
        else if(heading == Heading.WEST){
            x -= steps;
            if(x <= world.SIZE){
                x += world.SIZE;
            }
        }
        world.changed();
    }

    public abstract void update();
}
