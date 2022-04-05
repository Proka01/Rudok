package model.factory;

import app.MainFrame;
import errors.ErrorTypes;
import model.workspace.RuNode;
import model.workspace.RuNodeType;

public class FactoryManager {

    private static FactoryManager instance = null;

    public static FactoryManager getInstance() {
        if (instance==null){
            instance = new FactoryManager();
            instance.initialise();
        }
        return instance;
    }

    private ProjectFactory projectFactory;
    private PresentationFactory presentationFactory;
    private SlideFactory slideFactory;
    private DummyFactory dummyFactory;

    private void initialise(){
        projectFactory = new ProjectFactory();
        presentationFactory = new PresentationFactory();
        slideFactory = new SlideFactory();
        dummyFactory = new DummyFactory();
    }

    public RuNodeFactory getFactory(RuNode parentRuNode){
        if(parentRuNode.getRuNodeType().equals(RuNodeType.WORKSPACE))
            return projectFactory;
        else if(parentRuNode.getRuNodeType().equals(RuNodeType.PROJECT))
            return presentationFactory;
        else if(parentRuNode.getRuNodeType().equals(RuNodeType.PRESENTATION))
            return slideFactory;
        else if(parentRuNode.getRuNodeType().equals(RuNodeType.SLIDE)){
            dummyFactory.setErrorType(ErrorTypes.WRONG_COMPONENT_SELECTED); //na slajd se nista ne dodaje i ovaj null treba regulisati za errore
            return dummyFactory;
        }

        return null;
    }
}
