package MVCObserver.mvc;

import MVCObserver.observer.ISubscriber;
import app.MainFrame;
import gui.tabs.MyTabbedPane;
import MVCObserver.observer.DummyNotification;
import MVCObserver.observer.NotificationStatus;
import model.workspace.Project;

import javax.swing.*;
import java.awt.*;

public class ProjectView extends JPanel implements ISubscriber {

    private Project project;
    private MyTabbedPane myTabbedPane;
    private JLabel myLabel;

    public ProjectView() {
        initializeGUI();
        //this.setLayout(new GridLayout(0,1));
    }

    private void initializeGUI(){
        this.setPreferredSize(new Dimension(MainFrame.getInstance().getPanMainWindow().getWidth(),MainFrame.getInstance().getPanMainWindow().getHeight()));
        this.setBackground(new Color(165, 185, 204));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        if(project == null)
            myLabel = new JLabel("");
        else
            myLabel = new JLabel(project.getName());
        add(myLabel,BorderLayout.NORTH);
        myTabbedPane = new MyTabbedPane(project);
        this.add(myTabbedPane,BorderLayout.CENTER);
    }

    @Override
    public void update(Object notification) {

        if(notification instanceof DummyNotification){
            DummyNotification dummy = (DummyNotification) notification;

            if(this.project.equals(dummy.getProject())){
                if(dummy.getPresentation() == null && dummy.getStatus().equals(NotificationStatus.PROJECT_LOADED)){
                    initializeGUI();
                }
                else if(dummy.getPresentation() == null && dummy.getStatus().equals(NotificationStatus.PROJECT_NAME_CHANGED)){
                    myLabel.setText(project.getName());
                }
                else if(dummy.getStatus().equals(NotificationStatus.PRESENTATION_ADDED)){
                        myTabbedPane.addToTabs(dummy.getPresentation());
                }
                else if(dummy.getStatus().equals(NotificationStatus.PRESENTATION_REMOVED)){
                        myTabbedPane.removeFromTabs(dummy.getPresentation());
                }
                else if(dummy.getStatus().equals(NotificationStatus.PRESENTATION_NAME_CHANGED)){
                    myTabbedPane.getTabs().setTitleAt(dummy.getPresentationIndex(),dummy.getPresentation().getName());
                }
            }
        }

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getProjectView());
    }

    public void setProject(Project project) {
            this.removeAll();
            this.project = project;
            this.project.removeAllSubscribers(); //ove dve linije msm da su nepotrebne jer project vec
            this.project.addSubscriber(this);    //ima projectView u svojim subscrimerima
            this.myTabbedPane.setProject(project);
            this.update(new DummyNotification(this.project,null, NotificationStatus.PROJECT_LOADED)); //bilo je new Object
            //TODO: ovde pozvati update
    }

    public void removeProjec(){
        this.removeAll();
        this.project = null;
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getProjectView());
    }

    public Project getProject() {
        return project;
    }

    public MyTabbedPane getMyTabbedPane() {
        return myTabbedPane;
    }
}