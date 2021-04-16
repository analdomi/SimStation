package flocking;

import mvc.*;
import simstation.*;

class Bird extends Agent {

    protected int speed;

    public Bird() {
        super();
        heading = Heading.random();
        speed = Utilities.rng.nextInt(5) + 1;
    }

    public void update() {
        Bird neighbor = (Bird)this.world.getNeighbor(this, 1);
        heading = neighbor.heading;
        speed = neighbor.speed;
        move(speed);
    }


}


class FlockingFactory extends SimulationFactory {
    public Model makeModel() { return new FlockingSimulation(); }
    public String getTitle() { return "Flocking";}
}

public class FlockingSimulation extends Simulation {

    public void populate() {
        for(int i = 0; i < 15; i++){
            Bird newBird = new Bird();
//            newBird.xc = Utilities.rng.nextInt(100) - 50;
//            newBird.yc = Utilities.rng.nextInt(100) - 50;
            addAgent(newBird);
        }
    }

    public String[] getStats(){
        int[] speedCount = new int[]{0, 0, 0, 0, 0};
        for(Agent a : agents) {
            Bird b = (Bird)a;
            speedCount[b.speed-1]++;
        }
        String[] stats = new String[5];
        stats[0] = "#birds @ speed 1 = " + speedCount[0];
        stats[1] = "#birds @ speed 2 = " + speedCount[1];
        stats[2] = "#birds @ speed 3 = " + speedCount[2];
        stats[3] = "#birds @ speed 4 = " + speedCount[3];
        stats[4] = "#birds @ speed 5 = " + speedCount[4];
        return stats;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }

}
