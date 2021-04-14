package simstation;

import mvc.*;
import simstation.commands.*;

public class SimulationFactory implements AppFactory {

    public Model makeModel() { return new Simulation(); }

    public View makeView(Model m) {
        return new SimulationView((Simulation)m);
    }

    public String[] getEditCommands() { return new String[] {"Start", "Suspend", "Resume", "Stop", "Stats"}; }

    public Command makeEditCommand(Model model, String type) {
        if (type == "Start") {
            return new StartCommand(model);
        } else if (type == "Suspend") {
            return new SuspendCommand(model);
        } else if (type == "Resume") {
            return new ResumeCommand(model);
        } else if (type == "Stop") {
            return new StopCommand(model);
        } else if (type == "Stats") {
            return new StatsCommand(model);
        }
        return null;
    }

    public String getTitle() { return "Simstation"; }

    public String[] getHelp() {
        return new String[] {""};
    }

    public String about() {
        return "Simstation Version 1";
    }

}
}
