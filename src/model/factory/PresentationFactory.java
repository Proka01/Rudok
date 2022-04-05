package model.factory;

import MVCObserver.mvc.PresentationView;
import MVCObserver.mvc.ProjectView;
import app.MainFrame;
import errors.ErrorFactory;
import errors.ErrorTypes;
import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.RuNode;
import model.workspace.RuNodeType;

import javax.swing.*;

public class PresentationFactory extends RuNodeFactory{
    @Override
    public RuNode createModel(RuNode parentRuNode, int childCount) {

        /*Ukoliko se neki cvor obrise iz stabla on ostane upamcen kao lastSelComp i ako
        * se odma zatim klikne dugme za dodavanje tom cvoru, koji je obrisan, doda se dete sto je greska
        *
        * Nju prepoznajemo tako sto ukoliko neki cvor nije Workspace, a njegov roditelj je null, onda je to ta greska
        * Posle u newRuNode-u ukoliko factori vrati null paci se
        * ErrorFactory.getInstance().generateError(ErrorTypes.NOTHING_SELECTED_ERROR);
        */
        if(parentRuNode.getParent() == null)
            return null;

        String defaultURL = "src/images/black.jpg";

        //Kreiramo novu prezentaciju
        Presentation presentation = new Presentation(parentRuNode,"Presentation"+String.valueOf(childCount+1),"Author",defaultURL);
        presentation.setRuNodeType(RuNodeType.PRESENTATION);

        //napravimo novi presentationView i povezemo ga sa odgovarajucom prezentacijom
        PresentationView presentationView = new PresentationView(presentation);

        ProjectView projectView = MainFrame.getInstance().getProjectView();
        Project currentProject = (Project) parentRuNode;

        //Na pocetku projetView-u nije dodeljen ni jedan projekat
        //Pa mora da ide ovaj if
        if(MainFrame.getInstance().getProjectView().getProject() == null)
            projectView.setProject(currentProject);

        //Roditelju (Project) dodamo novo dete (presentation) u listu dece
        ((Project)parentRuNode).addChild(presentation);
        presentation.setParent(parentRuNode);
        presentation.getSharedProjects().add(parentRuNode);

        return presentation;
    }
}
