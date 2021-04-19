package simstation;

import mvc.*;
import java.awt.*;

public class SimulationView extends View {
    public SimulationView(Simulation simulation) {
        super(simulation);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Simulation simulation = (Simulation)model;

        Color oldColor = gc.getColor();

        for(Agent agent: simulation.getAgents()) {
            gc.setColor(agent.agentColor);
            gc.fillOval(agent.x, agent.y, 4, 4);
        }

        gc.setColor(oldColor);

    }
}
