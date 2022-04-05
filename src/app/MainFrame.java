package app;

import ActionManagers.ActionManager;
import MVCObserver.mvc.ProjectView;
import MVCObserver.observer.ISubscriber;
import OpentHandler.LoadWorkspaceHandler;
import comandPattern.CommandManager;
import controllers.MyWindowActionListener;
import errors.*;
import gui.Menu;
import gui.Toolbar;
import gui.tree.model.MyTreeModel;
import gui.tree.model.MyTreeNode;
import gui.tree.view.MyJTree;
import model.workspace.RuNodeType;
import model.workspace.Workspace;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class MainFrame extends JFrame implements ISubscriber {

    //glavni prozor aplikacije je singelton
    private static MainFrame instance = null;

    private Workspace workspace;

    private ActionManager actionManager;
    private CommandManager commandManager;
    //private StateManager stateManager;

    private gui.Menu menu;
    private Toolbar toolbar;

    private MyTreeModel myTreeModel;
    private MyJTree myJTree;

    private ProjectView projectView;
    private JPanel panMainWindow;
    private JPanel editStatePanel;
    //private SlideShowJPanel slideShowStatePanel;

    public ProjectView getProjectView() {
        return projectView;
    }

    public void setProjectView(ProjectView projectView) {
        this.projectView = projectView;
    }

    //od singeltona
    public static MainFrame getInstance() {
        if (instance==null){
            instance = new MainFrame();
            instance.initialise();
            //instance.initialiseGUI();
        }
        return instance;
    }

    //inicijalizacija ActionManagera
    private void initialise(){
        workspace = new Workspace(null,"Workspace", RuNodeType.WORKSPACE);

        actionManager = new ActionManager();
        commandManager = new CommandManager();
        //stateManager = new StateManager();

        //TODO: dodato 01/12/2021
        editStatePanel = new JPanel(new BorderLayout());
        //slideShowStatePanel = new SlideShowJPanel();

        this.initializeMyJTree();
        this.initialiseGUI();

        //TODO: dodato 01/12/2021
        this.add(editStatePanel);

        this.initialiseWorkspace();
    }

    private void initialiseWorkspace(){
        String lastUsedConextPath = null;
        try {
            File file = new File("context.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                lastUsedConextPath = myReader.nextLine();
            }
            myReader.close();
        }catch (Exception exception){
            exception.printStackTrace();
        }

       if(lastUsedConextPath != null){
           LoadWorkspaceHandler loadWorkspaceHandler = new LoadWorkspaceHandler();
           loadWorkspaceHandler.loadWorkspace(lastUsedConextPath);
       }

    }

    //postavljanje komponenti i njihovih stavku na MainFrame
    private void initialiseGUI(){
        //inicijalizacija osnovnih atributa prozora
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/2,screenHeight/2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("RuDok app");
        addWindowListener(new MyWindowActionListener());

        //dodavanje menu-a i toolbar-a na glavni prozor aplikacije
        menu = new Menu(this);
        setJMenuBar(menu);

        toolbar = new Toolbar();
        editStatePanel.add(toolbar,BorderLayout.NORTH);
        //add(toolbar,BorderLayout.NORTH);

        //implementacija splitPane-a

        JScrollPane panTree=new JScrollPane(myJTree);
        panTree.setMinimumSize(new Dimension(200,150));

        //panTree.setBackground(Color.green);
        //panTree.setPreferredSize(new Dimension(100,100));

        panMainWindow = new JPanel(new BorderLayout());
        panMainWindow.setBackground(Color.LIGHT_GRAY);
        projectView = new ProjectView();  //TODO: dodato 05/11/2021
        panMainWindow.add(projectView); //TODO: dodato 05/11/2021
        //panMainWindow.setPreferredSize(new Dimension(100,100));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panTree,panMainWindow);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        //TODO: dodato 01/12/2021
        editStatePanel.add(splitPane,BorderLayout.CENTER);

        //TODO: zakomentarisano 01/12/2021
        //add(splitPane);
    }

    private void initializeMyJTree(){
        myJTree = new MyJTree();
        myTreeModel = new MyTreeModel(new MyTreeNode(workspace));
        myJTree.setModel(myTreeModel);
    }

    //konstruktor
    public MainFrame(){

    }

    /**Getteri i setteri */
    public JPanel getPanMainWindow() {
        return panMainWindow;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public JMenuBar getMenu() {
        return menu;
    }

    public JToolBar getToolBar() {
        return toolbar;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public MyTreeModel getMyTreeModel() {
        return myTreeModel;
    }

    public void setMyTreeModel(MyTreeModel myTreeModel) {
        this.myTreeModel = myTreeModel;
    }

    public MyJTree getMyJTree() {
        return myJTree;
    }

    public void setMyJTree(MyJTree myJTree) {
        this.myJTree = myJTree;
    }

    public JPanel getEditStatePanel() {
        return editStatePanel;
    }

    public void setEditStatePanel(JPanel editStatePanel) {
        this.editStatePanel = editStatePanel;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    /**Upravljanje error dijalozima */
    @Override
    public void update(Object notification) {
        if(notification instanceof NothingSelectedError)
            JOptionPane.showMessageDialog(this, ((NothingSelectedError) notification).errorMessage());
        else if(notification instanceof NoPermissionError)
            JOptionPane.showMessageDialog(this, ((NoPermissionError) notification).errorMessage());
        else if(notification instanceof InvalidPictureExtensionError)
            JOptionPane.showMessageDialog(this, ((InvalidPictureExtensionError) notification).errorMessage());
        else if(notification instanceof EmptyAuthorNameError)
            JOptionPane.showMessageDialog(this, ((EmptyAuthorNameError) notification).errorMessage());
        else if(notification instanceof WrongComponentError)
            JOptionPane.showMessageDialog(this, ((WrongComponentError) notification).errorMessage());
        else if(notification instanceof NoOpenedPresentationError)
            JOptionPane.showMessageDialog(this,((NoOpenedPresentationError) notification).errorMessage());
        else if(notification instanceof IntersectingSlotsError)
            JOptionPane.showMessageDialog(this,((IntersectingSlotsError) notification).errorMessage());

    }
}
