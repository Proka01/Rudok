package gui.radioButtonsControllers;

import MVCObserver.mvc.PresentationView;
import app.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyRadioButtonActionListener implements ActionListener {

    private JRadioButton jrb1;
    private JRadioButton jrb2;

    public MyRadioButtonActionListener(JRadioButton jrb1, JRadioButton jrb2) {
        this.jrb1 = jrb1;
        this.jrb2 = jrb2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(jrb1.isSelected()){
            System.out.println("full");
        }
        else if(jrb2.isSelected()){
            System.out.println("dashed");
        }
    }
}
