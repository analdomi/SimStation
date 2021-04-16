package mvc;

import javax.swing.*;
import java.beans.*;


public class View extends JPanel implements PropertyChangeListener {
    protected Model model;

    public View(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setModel(Model model) {
        this.model.removePropertyChangeListener(this);
        this.model = model;
        this.model.initSupport();
        this.model.addPropertyChangeListener(this);
        repaint();
    }

    public void propertyChange(PropertyChangeEvent arg0) {
        repaint();
    }

}
