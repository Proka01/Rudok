package gui;

import MVCObserver.mvc.SlideView;
import app.MainFrame;
import model.workspace.Presentation;
import model.workspace.RuNode;
import model.workspace.Slide;

import javax.swing.*;
import java.awt.*;

public class EditSlidesJPanel extends JPanel {

    private Presentation presentation;
    private JLabel authorNameJL;
    private JPanel previewPanel;
    private JPanel slidePanel;
    private JScrollPane previewPanelScrollPane;
    private JScrollPane slidePanelScrollPane;
    private JSplitPane jSplitPane;
    private ToolbarPresentationView toolbar;
    private JPanel toolbarAuthorJPanel;

    public EditSlidesJPanel(Presentation presentation) {
        this.presentation = presentation;
        this.setLayout(new BorderLayout());
        initializeGUI();
    }

    private void initializeGUI(){
        toolbarAuthorJPanel = new JPanel(new BorderLayout());
        toolbar = new ToolbarPresentationView();
        toolbarAuthorJPanel.add(toolbar,BorderLayout.NORTH);
        authorNameJL = new JLabel(presentation.getAuthorName());

        toolbarAuthorJPanel.add(authorNameJL,BorderLayout.CENTER);
        add(toolbarAuthorJPanel,BorderLayout.NORTH);

        previewPanel = new JPanel();
        slidePanel = new JPanel();

        previewPanel.setLayout(new BoxLayout(previewPanel,BoxLayout.Y_AXIS));
        previewPanel.setBackground(Color.gray);
        slidePanel.setBackground(Color.lightGray);
        slidePanel.setLayout(new BoxLayout(slidePanel,BoxLayout.Y_AXIS));

        previewPanelScrollPane = new JScrollPane(previewPanel);
        slidePanelScrollPane = new JScrollPane(slidePanel);

        jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,previewPanelScrollPane,slidePanelScrollPane);
        jSplitPane.setOneTouchExpandable(true);
        jSplitPane.setDividerLocation(150);
        this.add(jSplitPane,BorderLayout.CENTER);

        initializePreviewAndSlideViews();
    }

    public void initializePreviewAndSlideViews(){
        slidePanel.removeAll();
        previewPanel.removeAll();

        for(RuNode node : presentation.getSlides()){
            if(node instanceof Slide){

                slidePanel.add((SlideView)((Slide)node).getSubscribers().get(0));
                slidePanel.add(Box.createVerticalStrut(10));

                previewPanel.add((SlideView)((Slide)node).getSubscribers().get(1));
                previewPanel.add(Box.createVerticalStrut(5));
            }
        }
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getProjectView());
    }

    /** metode */

    public void reloadToolbarJPanel(){
        toolbarAuthorJPanel.removeAll();
        toolbarAuthorJPanel.add(toolbar,BorderLayout.NORTH);
        toolbarAuthorJPanel.add(authorNameJL,BorderLayout.CENTER);
    }

    public JLabel getAuthorNameJL() {
        return authorNameJL;
    }

    /** Getters and setters */


    public JPanel getToolbarAuthorJPanel() {
        return toolbarAuthorJPanel;
    }

    public void setToolbarAuthorJPanel(JPanel toolbarAuthorJPanel) {
        this.toolbarAuthorJPanel = toolbarAuthorJPanel;
    }

    public JSplitPane getjSplitPane() {
        return jSplitPane;
    }

    public ToolbarPresentationView getToolbar() {
        return toolbar;
    }
}
