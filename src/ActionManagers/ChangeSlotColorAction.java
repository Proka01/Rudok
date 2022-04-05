package ActionManagers;

import MVCObserver.mvc.PresentationView;
import app.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ChangeSlotColorAction extends AbstractRudokAction{

    private Color currColor = Color.red;

    public ChangeSlotColorAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/paint.png"));
        putValue(NAME,"Change color");
        putValue(SHORT_DESCRIPTION,"Change color");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color newColor = JColorChooser.showDialog(null, "Choose a color", currColor);
        currColor = newColor;
    }

    public Color getCurrColor() {
        return currColor;
    }
}
