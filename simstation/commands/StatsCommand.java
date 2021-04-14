package simstation.commands;
import mvc.*;
import simstation.*;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation simulation = (Simulation) model;

        String[] stats = simulation.getStats();
        String statsMessage = "";
        for(String s : stats) {
            statsMessage += s + "\n";
        }
        mvc.Utilities.inform(statsMessage);
    }
}
