package MVCObserver.controller;

import model.workspace.Slide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlideController implements ActionListener {

    private Slide slide;

    public SlideController(Slide slide) {
        this.slide = slide;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
