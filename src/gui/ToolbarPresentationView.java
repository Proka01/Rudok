package gui;

import app.MainFrame;
import gui.radioButtonsControllers.MyRadioButtonActionListener;

import javax.swing.*;
import java.awt.*;

public class ToolbarPresentationView extends JToolBar {

    private JSpinner jSpinner;
    private JRadioButton jrb1;
    private JRadioButton jrb2;
    private ButtonGroup buttonGroup;
    private JComboBox jComboBox;

    public ToolbarPresentationView(){
        super(JToolBar.HORIZONTAL);
        setFloatable(false);
        jSpinner = new JSpinner(new SpinnerNumberModel(1,1,10,1));
        jSpinner.setBounds(70, 70, 10, 40);
        jSpinner.setMaximumSize(new Dimension(60,30));

        buttonGroup = new ButtonGroup();
        jrb1 = new JRadioButton("full");
        jrb1.setSelected(true);
        jrb2 = new JRadioButton("dashed");
        jrb1.addActionListener(new MyRadioButtonActionListener(jrb1,jrb2));
        jrb2.addActionListener(new MyRadioButtonActionListener(jrb1,jrb2));
        buttonGroup.add(jrb1);
        buttonGroup.add(jrb2);
        String[] options = {"TEXT", "MULTIMEDIA"};
        jComboBox = new JComboBox(options);
        jComboBox.setBounds(70, 70, 10, 40);
        jComboBox.setMaximumSize(new Dimension(100,30));

        add(MainFrame.getInstance().getActionManager().getStartSlideShowAction());
        addSeparator(new Dimension(15,25));
        add(MainFrame.getInstance().getActionManager().getAddSlotAction());
        add(MainFrame.getInstance().getActionManager().getRemoveSlotAction());
        add(MainFrame.getInstance().getActionManager().getMoveSlotAction());
        add(MainFrame.getInstance().getActionManager().getSelectSlotStateAction());
        addSeparator(new Dimension(15,25));
        add(MainFrame.getInstance().getActionManager().getChangeSlotColorAction());
        add(jSpinner);
        add(jrb1);
        add(jrb2);
        add(jComboBox);
        add(MainFrame.getInstance().getActionManager().getOpenSlotContentDialog());
    }

    public JSpinner getjSpinner() {
        return jSpinner;
    }

    public void setjSpinner(JSpinner jSpinner) {
        this.jSpinner = jSpinner;
    }

    public JRadioButton getJrb1() {
        return jrb1;
    }

    public void setJrb1(JRadioButton jrb1) {
        this.jrb1 = jrb1;
    }

    public JRadioButton getJrb2() {
        return jrb2;
    }

    public void setJrb2(JRadioButton jrb2) {
        this.jrb2 = jrb2;
    }

    public JComboBox getjComboBox() {
        return jComboBox;
    }
}
