package ActionManagers;

import app.MainFrame;
import comandPattern.AddRuNodeCommand;
import comandPattern.DeepSlideCopyCommand;
import errors.ErrorFactory;
import errors.ErrorTypes;
import gui.tree.model.MyTreeNode;
import model.factory.DummyFactory;
import model.factory.FactoryManager;
import model.factory.RuNodeFactory;
import model.workspace.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewRuNode extends AbstractRudokAction{

    private FactoryManager factoryManager = FactoryManager.getInstance();

    public NewRuNode(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/newFile.png"));
        putValue(NAME,"Add");
        putValue(SHORT_DESCRIPTION,"Add");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MyTreeNode lastSelectedComponent = (MyTreeNode) MainFrame.getInstance().getMyJTree().getLastSelectedPathComponent();
        if(lastSelectedComponent == null){
            ErrorFactory.getInstance().generateError(ErrorTypes.NOTHING_SELECTED_ERROR);
            return;
        }

        MyTreeNode parentTreeNode = lastSelectedComponent;
        RuNode parentRuNode = lastSelectedComponent.getNode();

        if(parentTreeNode != null){
            int childCount = parentTreeNode.getChildCount();

            //RuNodeFactory ruNodeFactory = RuNodeFactory.getFactory(parentRuNode);
            RuNodeFactory ruNodeFactory = factoryManager.getFactory(parentRuNode);
            if(ruNodeFactory instanceof DummyFactory){
                ErrorFactory.getInstance().generateError(ErrorTypes.WRONG_COMPONENT_SELECTED);
                return;
            }

            RuNode childRuNode = ruNodeFactory.getNewRuNode(parentRuNode,childCount);
            if(childRuNode == null){
                ErrorFactory.getInstance().generateError(ErrorTypes.NOTHING_SELECTED_ERROR);
                return;
            }

            MyTreeNode childTreeNode = new MyTreeNode(childRuNode);
            //parentTreeNode.add(childTreeNode);

            MainFrame.getInstance().getCommandManager().addCommand(new AddRuNodeCommand(parentTreeNode,childTreeNode));
            //MainFrame.getInstance().getCommandManager().addCommand(new DeepSlideCopyCommand(parentTreeNode,childTreeNode));
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
        }
        else
            ErrorFactory.getInstance().generateError(ErrorTypes.NOTHING_SELECTED_ERROR);

    }
}
