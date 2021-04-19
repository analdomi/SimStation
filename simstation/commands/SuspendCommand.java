package simstation.commands;
import mvc.*;
import simstation.*;

public class SuspendCommand extends Command {
    public SuspendCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation simulation = (Simulation) model;

        if(!simulation.getAgentSuspended()){
            simulation.suspend();
            simulation.changed();
        }
    }

}
