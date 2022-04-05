package MVCObserver.controller;

import model.workspace.Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresentationController implements ActionListener {

    private Presentation presentation;

    public PresentationController(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
