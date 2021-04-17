package plauge;

import mvc.*;
import simstation.*;

import java.awt.*;

class Plague extends Agent {

    private boolean infected;
    public int resistance,luck;

    public Plague() {
        super();
        luck = mvc.Utilities.rng.nextInt(100);
        resistance = mvc.Utilities.rng.nextInt(PlagueSimulation.RESISTANCE); //each agent has a varying resistance from 0-40
        infected = luck < PlagueSimulation.INITIAL_PERCENT_INFECTED;
        heading = Heading.random();
    }

    public synchronized boolean isInfected(){return infected;}

    public void setInfected(boolean infected){
        this.infected = infected;
        this.setAgentColor(Color.red);
    }

    public void update() {
        if(this.isInfected()){
            Plague neighbor = (Plague)world.getNeighbor(this, PlagueSimulation.VIRULENCE);
            if(neighbor != null && !neighbor.isInfected()){ //There is uninfected neighbor
                if(resistance < Utilities.rng.nextInt(100)){
                    neighbor.setInfected(true);
                }
            }
        }
        move(2);
    }
}


class PlagueFactory extends SimulationFactory {
    public Model makeModel() { return new PlagueSimulation(); }
    public String getTitle() { return "Plague";}
}

public class PlagueSimulation extends Simulation {

    public static int VIRULENCE = 10; //Infection radius
    public static int RESISTANCE = 40; //Resistance of each agent varies. 40 is the greatest resistance
    public static int INITIAL_PERCENT_INFECTED = 15;

    public void populate() {
        for(int i = 0; i < 30; i++){ //Start half are infected
            Plague newPlague = new Plague();
            if(newPlague.isInfected()){
                newPlague.setAgentColor(Color.red);
            }
            else{
                newPlague.setAgentColor(Color.green);
            }
            addAgent(newPlague);
        }
    }

    public double getInfectedPercent(){
        double infected = 0.0;
        for(Agent a : agents) {
            Plague p = (Plague) a;
            if(p.isInfected()){
                infected++;
            }
        }

        return (infected/(double)agents.size())*100.0;
    }

    public String[] getStats(){
        String[] stats = new String[3];
        stats[0] = "#agents = " + agents.size();
        stats[1] = "clock = " + this.getClock();
        stats[2] = "% infected = " + this.getInfectedPercent();
        return stats;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }

}
