package simstation;

import mvc.*;
import java.util.List;

public class Simulation extends Model {
    private int clock = 0;
    private List<Agent> agents;

    public Simulation() {

    }

    public void start() {
        for(Agent agent: agents) {
            Thread thread = new Thread(agent);
            thread.start();
        }
    }

    public void suspend() {
        for(Agent agent: agents) {
            agent.suspend();
        }
    }

    public void resume() {
        for(Agent agent: agents) {
            agent.resume();
        }
    }

    public void stop() {
        for(Agent agent: agents) {
            agent.stop();
        }
    }

    public Agent getNeighbor(Agent a) {

    }

    public void populate() {

    }
}
