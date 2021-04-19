package Prisoner;

import mvc.*;
import simstation.*;

interface Behavior extends Serializable {
    boolean cooperate(Prisoner p);
}

class Cooperate implements Behavior {
    public boolean cooperate(Prisoner p) {
        return true;
    }
}
class RandomlyCooperate implements Behavior {
    public boolean cooperate(Prisoner p) {
        if(Math.random()<0.5)
            return true;
        else
            return false;
    }
}
class Cheat implements Behavior {
    public boolean cooperate(Prisoner p) {
        return false;
    }
}
class Tit4Tat implements Behavior {
    public boolean cooperate(Prisoner p) {
        if(p.cheated)
            return false;
        else
            return true;
    }
}

class Prisoner extends Agent {
    Behavior behavior;
    int fitness;
    int strategy;
    boolean cheated; //if prisoner has been cheated before
    public Prisoner() {
        super();
        fitness = 0;
        cheated = false;
        heading = Heading.random();
    }

    public void play(Prisoner p) {
        if(behavior.cooperate(this) && behavior.cooperate(p)) {
            this.updateFitness(3);
            this.cheated = false;
            p.updateFitness(3);
            p.cheated = false;
        }
        else if(behavior.cooperate(this) && !behavior.cooperate(p)) {
            p.updateFitness(5);
            p.cheated = false;
            this.cheated = true;
        }
        else if(!behavior.cooperate(this) && behavior.cooperate(p)) {
            this.updateFitness(5);
            this.cheated = false;
            p.cheated = true;
        }
        else {
            this.updateFitness(1);
            this.cheated = true;
            p.updateFitness(1);
            p.cheated = true;
        }
    }

    public synchronized void updateFitness(int f) {
        this.fitness += f;
    }

    public void update() {
        Prisoner neighbor = (Prisoner)world.getNeighbor(this, 10);
        if(neighbor != null) {
            neighbor.play(this);
        }
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }
}

class PrisonerDilemmaFactory extends SimulationFactory {
    public Model makeModel() { return new PrisonerDilemmaSimulation(); }
    public String getTitle() { return "Prisoner Dilemma";}
}

public class PrisonerDilemmaSimulation extends Simulation {

    int Prisoners = 20; //min 4
    public void populate() {
        int behavior;
        for(int i = 0; i < Prisoners; i++){
            behavior = mvc.Utilities.rng.nextInt(100);
            Prisoner newPrisoner = new Prisoner();
            if(behavior < 25){
                newPrisoner.behavior = new Cooperate();
                newPrisoner.strategy = 0;
            }
            else if(behavior >= 25 && behavior < 50) {
                newPrisoner.behavior = new RandomlyCooperate();
                newPrisoner.strategy = 1;
            }
            else if(behavior >= 50 && behavior < 75){
                newPrisoner.behavior = new Cheat();
                newPrisoner.strategy = 2;
            }
            else {
                newPrisoner.behavior = new Tit4Tat();
                newPrisoner.strategy = 3;
            }
            addAgent(newPrisoner);
        }
    }


    public String[] getStats(){
        int[] avgFitness = new int[]{0, 0, 0, 0};
        for(Agent a : agents) {
            Prisoner p = (Prisoner)a;
            avgFitness[p.strategy] += p.fitness;
        }
        String[] stats = new String[5];
        stats[0] = "clock = " + this.getClock();
        stats[1] = "Avg Fitness of Cooperate = " + avgFitness[0] / (double)(Prisoners/4);
        stats[2] = "Avg Fitness of RandomCooperate = " + avgFitness[1] / (double)(Prisoners/4);
        stats[3] = "Avg Fitness of Cheat = " + avgFitness[2] / (double)(Prisoners/4);
        stats[4] = "Avg Fitness of Tit4Tat = " + avgFitness[3] / (double)(Prisoners/4);
        return stats;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PrisonerDilemmaFactory());
        panel.display();
    }

}
