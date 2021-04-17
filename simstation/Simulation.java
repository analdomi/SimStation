package simstation;

import mvc.*;
import java.util.*;

public class Simulation extends Model {
    protected int clock;
    protected List<Agent> agents;
    private Timer timer;
    public static int SIZE = 250;

    public Simulation() {
        agents = new LinkedList<>();
        clock = 0;
    }

    public void start() {
        agents = new LinkedList<>();
        clock = 0;
        populate();
        startTimer();
        for(Agent agent: agents) {
            Thread thread = new Thread(agent);
            thread.start();
        }
    }

    public synchronized void suspend() {
        for(Agent agent: agents) {
            agent.suspend();
        }
        stopTimer();
    }

    public synchronized void resume() {
        startTimer();
        for(Agent agent: agents) {
            agent.resume();
        }
    }

    public synchronized void stop() {
        stopTimer();
        for(Agent agent: agents) {
            agent.stop();
        }
    }

    public synchronized int getClock(){
        return  clock;
    }

    public synchronized void incClock(){
        clock++;
    }

    private void startTimer(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    public synchronized Agent getNeighbor(Agent asker, double radius) {
        Agent neighbor = null;
        boolean found = false;
        int i = Utilities.rng.nextInt(agents.size());
        int start = i;
        while(!found){
            Agent canidate = agents.get(i);
            if(canidate != asker && asker.distance(canidate) < radius){
                neighbor = agents.get(i);
                found = true;
            } else{
                i = (i+1) % agents.size();
                if(i == start) break;
            }
        }
        return neighbor;
    }

    public void populate(){}

    public synchronized void addAgent(Agent a){
        agents.add(a);
        a.setWorld(this); //not sure what this does but prof says very important
    }

    public synchronized void removeAgent(Agent a ){
        agents.remove(a);
        a.stop();
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public String[] getStats(){
        String[] stats = new String[2];
        stats[0] = "#agents = " + agents.size();
        stats[1] = "clock = " + this.getClock();
        return stats;
    }

    private class ClockUpdater extends TimerTask {
        public void run(){
            incClock();
        }
    }
}
