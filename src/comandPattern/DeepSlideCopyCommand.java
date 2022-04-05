package comandPattern;

import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.Slide;

public class DeepSlideCopyCommand extends AbstractCommand {
    private MyTreeNode myTreeNodeParent;
    private MyTreeNode myTreeNodeChild;

    public DeepSlideCopyCommand(MyTreeNode myTreeNodeParent, MyTreeNode myTreeNodeChild) {
        this.myTreeNodeParent = myTreeNodeParent;
        this.myTreeNodeChild = myTreeNodeChild;
    }

    @Override
    public void doCommand() {
        if(myTreeNodeChild.getNode() instanceof Slide){
            Slide slide = (Slide)(myTreeNodeChild.getNode());
            Presentation presentation = (Presentation) (myTreeNodeParent.getNode());
            deepCopy(slide,presentation, (MyTreeNode) myTreeNodeParent.getParent());
        }
        /*else{
            int br = MainFrame.getInstance().getCommandManager().getCurrentCommand();
            MainFrame.getInstance().getCommandManager().setCurrentCommand(br-1);
        }

         */
    }

    @Override
    public void undoCommand() {

    }

    private void deepCopy(Slide slide, Presentation pres, MyTreeNode myTreeNodeParent){
        MyTreeNode root = (MyTreeNode) MainFrame.getInstance().getMyJTree().getModel().getRoot();

        for(int i = 0; i < root.getChildCount(); i++){
            MyTreeNode childProject = (MyTreeNode) root.getChildAt(i);
            Project project = (Project) childProject.getNode();

            if(pres.getSharedProjects().contains(project) && !project.equals(myTreeNodeParent.getNode())){
                for(int j = 0; j < childProject.getChildCount(); j++){
                    MyTreeNode childPres = (MyTreeNode) childProject.getChildAt(j);
                    if(childPres.getNode().equals(pres)){
                        MyTreeNode newSlideMyTreeNode = new MyTreeNode(slide);
                        childPres.add(newSlideMyTreeNode);
                    }
                }
            }
        }
    }

    //private void deepUndoCopy()

}
