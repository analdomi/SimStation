package simstation;

import mvc.*;
import java.awt.*;

public abstract class Agent extends Bean implements Runnable {

    public Heading heading;
    public int x, y;
    public boolean suspended, stopped;
    public transient Thread myThread;
    protected Simulation world;
    protected Color agentColor;

    public Agent(){
        suspended = false;
        stopped = false;
        myThread = null;
        agentColor = Color.yellow;
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
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public synchronized void suspend() {
        suspended = true;
    }

    public synchronized void resume() {
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


    public int distance(Agent a){
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
            if(x <= 0){
                x += world.SIZE;
            }
        }
        world.changed();
    }
    public void setAgentColor(Color agentColor) {
        this.agentColor = agentColor;
    }

    public abstract void update();
}
