package comandPattern;

import MVCObserver.mvc.ProjectView;
import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteRuNodeCommand extends AbstractCommand{

    MyTreeNode myTreeNodeChild;
    MyTreeNode myTreeNodeParent;

    List<MyTreeNode> sharedParents = new ArrayList<>();
    List<MyTreeNode> sharedCopys = new ArrayList<>();
    List<Integer> sharedIndexes = new ArrayList<>();
    int ex_index = -1;

    public DeleteRuNodeCommand(MyTreeNode myTreeNodeParent, MyTreeNode myTreeNodeChild) {
        this.myTreeNodeChild = myTreeNodeChild;
        this.myTreeNodeParent = myTreeNodeParent;
    }

    @Override
    public void doCommand() {
        RuNode ruNodeChild = myTreeNodeChild.getNode();
        RuNode ruNodeParent = myTreeNodeParent.getNode();

        for(int i = 0; i < myTreeNodeParent.getChildCount(); i++){
            if(myTreeNodeParent.getChildAt(i).equals(myTreeNodeChild)){ex_index = i; break;}
        }
        myTreeNodeChild.removeFromParent();

        if(ruNodeChild instanceof Presentation){
            if(((Presentation)ruNodeChild).getSharedProjects().size() > 1){
                deepDeletePres(myTreeNodeChild,myTreeNodeParent);
            }
        }
        else if(ruNodeChild instanceof Slide){
            if(((Presentation)ruNodeParent).getSharedProjects().size() > 1){
                deepDeletSlides(myTreeNodeChild,myTreeNodeParent);
            }
        }

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
    }

    @Override
    public void undoCommand() {
        RuNode ruNodeChild = myTreeNodeChild.getNode();
        RuNode ruNodeParent = myTreeNodeParent.getNode();
        myTreeNodeParent.insert(myTreeNodeChild,ex_index);

        if(ruNodeParent instanceof Project){
            ProjectView projectView = MainFrame.getInstance().getProjectView();
            Project currentProject = (Project) ruNodeParent;

            //Na pocetku projetView-u nije dodeljen ni jedan projekat
            //Pa mora da ide ovaj if
            if(MainFrame.getInstance().getProjectView().getProject() == null)
                projectView.setProject(currentProject);
        }

        if(ruNodeChild instanceof Presentation){
            if(((Presentation)ruNodeChild).getSharedProjects().size() > 1){
                deepAddPres(myTreeNodeChild);
            }
        }
        else if(ruNodeChild instanceof Slide){
            if(((Presentation)ruNodeParent).getSharedProjects().size() > 1){
                deepAddSlides();
            }
        }

        ruNodeChild.setParent(ruNodeParent);
        ((RuNodeComposite)ruNodeParent).addChild(ruNodeChild);

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
    }

    private void deepDeletePres(MyTreeNode myTreeNodeChild, MyTreeNode myTreeNodeParent){
        Presentation pres = ((Presentation) myTreeNodeChild.getNode());
        MyTreeNode root = (MyTreeNode) MainFrame.getInstance().getMyJTree().getModel().getRoot();

        for(int i = 0; i < root.getChildCount(); i++){
            MyTreeNode childProject = (MyTreeNode) root.getChildAt(i);
            Project project = (Project) childProject.getNode();

            if(pres.getSharedProjects().contains(project) && !myTreeNodeParent.equals(childProject)){
                sharedParents.add(childProject);
                for(int j = 0; j < childProject.getChildCount(); j++){
                    if(((MyTreeNode)childProject.getChildAt(j)).getNode().equals(pres)){
                        sharedCopys.add((MyTreeNode)childProject.getChildAt(j));
                        childProject.remove(((MyTreeNode)childProject.getChildAt(j)));
                        break;
                    }
                }
            }
        }
    }

    private void deepDeletSlides(MyTreeNode myTreeNodeChild, MyTreeNode myTreeNodeParent){
        Slide slide = ((Slide) myTreeNodeChild.getNode());
        Presentation pres = ((Presentation) myTreeNodeParent.getNode());
        MyTreeNode root = (MyTreeNode) MainFrame.getInstance().getMyJTree().getModel().getRoot();

        for(int i = 0; i < root.getChildCount(); i++){
            MyTreeNode childProject = (MyTreeNode) root.getChildAt(i);
            Project project = (Project) childProject.getNode();

            if(pres.getSharedProjects().contains(project) && !myTreeNodeParent.getParent().equals(childProject)){
                //sharedParents.add(childProject);
                for(int j = 0; j < childProject.getChildCount(); j++){
                    if(((MyTreeNode)childProject.getChildAt(j)).getNode().equals(pres)){
                        for(int k = 0; k < ((MyTreeNode)childProject.getChildAt(j)).getChildCount(); k++){
                            MyTreeNode presMTN = ((MyTreeNode)childProject.getChildAt(j));
                            MyTreeNode slideMTN = (MyTreeNode) ((MyTreeNode) presMTN).getChildAt(k);
                            if(slideMTN.getNode().equals(slide)){
                                sharedIndexes.add(k);
                                MyTreeNode removeMTN = (MyTreeNode) (((MyTreeNode)childProject.getChildAt(j)).getChildAt(k));
                                sharedCopys.add((MyTreeNode)childProject.getChildAt(j).getChildAt(k));
                                sharedParents.add(((MyTreeNode)childProject.getChildAt(j)));
                                ((MyTreeNode)childProject.getChildAt(j)).remove(removeMTN);
                                break;
                            }
                        }
                    }
                }
            }

        }

    }

    private void deepAddPres(MyTreeNode myTreeNodeChild){
        for(int i = 0; i < sharedParents.size(); i++){
            sharedParents.get(i).add(sharedCopys.get(i));
        }
    }

    private void deepAddSlides(){
        for(int i = 0; i < sharedParents.size(); i++){
            sharedParents.get(i).insert(sharedCopys.get(i),sharedIndexes.get(i));
        }
    }

}
