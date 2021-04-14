package simstation;

import mvc.*;
import java.awt.*;

public class SimulationView extends View {
    public SimulationView(Simulation simulation) {
        super(simulation);
        setBackground(Color.gray);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Simulation simulation = (Simulation)model;

        Color oldColor = gc.getColor();

        for(Agent agent: simulation.getAgents()) {
            gc.setColor(Color.yellow);
            gc.fillOval(agent.xc, agent.yc, 4, 4);
        }

        gc.setColor(oldColor);

    }
}
