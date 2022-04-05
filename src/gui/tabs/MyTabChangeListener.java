package gui.tabs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyTabChangeListener implements ChangeListener {

    private JTabbedPane jTabbedPane;

    public MyTabChangeListener(JTabbedPane jTabbedPane) {
        this.jTabbedPane = jTabbedPane;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println("Selected tab: "+jTabbedPane.getSelectedIndex());
        /** Na osnovu ovog inedexa iz liste sa prezentacijama znamo na kojoj smo
         * project.getPresentations.get(index)
         *
         * Ta prezentacija za svog subscribera ima presentationView koji treba da se prikaze za taj tab
         *
         * */

    }
}
