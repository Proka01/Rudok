package gui.tabs;

import MVCObserver.mvc.PresentationView;
import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.RuNode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyTabbedPane extends JPanel{

    private JTabbedPane tabs;
    private Project project;
    private List<PresentationView> presentationsList = new ArrayList<>();

    public MyTabbedPane(Project project) throws HeadlessException {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(165, 185, 204));
        this.project = project;
        tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        this.add(tabs,BorderLayout.CENTER);
        loadPresentationList();
        initialize();
    }

    private int getIndex(PresentationView presentationView){
        int br = -1;
        for(PresentationView pw : presentationsList){
            br++;
            if(presentationView.equals(pw))
                return br;
        }

        return br;
    }

    public void addToTabs(Presentation presentation){
        loadPresentationList();
        PresentationView presentationView = (PresentationView) presentation.getSubscribers().get(0);
        presentationView.setParentTabbedPane(tabs);

        //TODO: zakomentarisano 01/12/2021
        JScrollPane jScrollPane = new JScrollPane(presentationView);
        //tabs.add(presentation.getName(),jScrollPane);
        tabs.add(presentation.getName(),presentationView);

    }

    public void removeFromTabs(Presentation presentation){
        loadPresentationList();
        System.out.println("----------------------");
        System.out.println(project.getName()+"   "+project.getName());
        PresentationView presentationView = (PresentationView) presentation.getSubscribers().get(0);
        int index = getIndex(presentationView);
        if(index != -1){
            tabs.removeTabAt(index);
            //TODO: dodato 02/12/2021
            presentationsList.remove(presentationsList.get(index));
        }

        //TODO: zakomentarisano 02/12/2021
        //loadPresentationList();
    }

    public void loadPresentationList(){
        presentationsList = new ArrayList<>();
        if(project != null){
            for(RuNode pres : project.getPresentations()){
                if(pres instanceof Presentation)
                    presentationsList.add((PresentationView) ((Presentation)pres).getSubscribers().get(0));
            }
        }
        System.out.println("||||||||||| "+presentationsList.size());
    }

    private void initialize(){
        tabs.removeAll();

        for(PresentationView pw : this.presentationsList){
            pw.setParentTabbedPane(tabs);
            JScrollPane jScrollPane = new JScrollPane(pw);
            //TODO: zakomentarisano 01/12/2021
            //tabs.add(pw.getPresentation().getName(),jScrollPane);
            tabs.add(pw.getPresentation().getName(),pw);
        }

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
    }

    public JTabbedPane getTabs() {
        return tabs;
    }

    public void setTabs(JTabbedPane tabs) {
        this.tabs = tabs;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
        loadPresentationList();
        initialize();
    }

    public List<PresentationView> getPresentationsList() {
        return presentationsList;
    }

    public void setPresentationsList(List<PresentationView> presentationsList) {
        this.presentationsList = presentationsList;
    }
}
