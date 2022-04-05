package OpentHandler;

import gui.tree.model.MyTreeNode;
import model.workspace.Project;

public class ImportPresentation {
    public void importToProject(MyTreeNode projectTreeNode, MyTreeNode presentationTreeNode){
        LoadPresenatationHandler loadPresenatationHandler = new LoadPresenatationHandler(projectTreeNode);
        loadPresenatationHandler.load(presentationTreeNode.getNode());
    }
}
