package simstation.commands;
import mvc.*;
import simstation.*;

public class ResumeCommand extends Command {
    public ResumeCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation simulation = (Simulation) model;

        if(simulation.getAgentSuspended()){
            simulation.resume();
            simulation.changed();
        }
    }
}
