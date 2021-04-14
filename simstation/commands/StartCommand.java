package simstation.commands;
import mvc.*;
import simstation.*;

public class StartCommand extends Command {
    public StartCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation simulation = (Simulation) model;

        simulation.start();
        simulation.changed();
    }
}
