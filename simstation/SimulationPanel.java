package simstation;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends AppPanel {
        public SimulationPanel(AppFactory factory) {
        super(factory);

        // add buttons to control panel
        controlPanel.setLayout(new GridLayout(5, 1));
        String[] buttons = {"Start", "Suspend", "Resume", "Stop", "Stats"};
        for(String buttonName : buttons) {
            JButton btn = new JButton(buttonName);
            btn.addActionListener(this);
            JPanel btnPanel = new JPanel();
            btnPanel.setLayout(new FlowLayout());
            btnPanel.setBackground(Color.pink);
            btnPanel.add(btn);
            controlPanel.add(btnPanel);
        }
    }

    public static void main(String[] args) {
        AppFactory factory = new SimulationFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }
}
