package model.factory;

import errors.ErrorTypes;
import model.workspace.*;

public abstract class RuNodeFactory {

    public RuNode getNewRuNode(RuNode parentRuNode, int childCount){
        RuNode newChildRuNode = createModel(parentRuNode, childCount);

        return newChildRuNode;
    }

    public abstract RuNode createModel(RuNode parentRuNode, int childCount);

    /*
    public static RuNodeFactory getFactory(RuNode parentRuNode){
        if(parentRuNode.getRuNodeType().equals(RuNodeType.WORKSPACE))
            return new ProjectFactory();
        else if(parentRuNode.getRuNodeType().equals(RuNodeType.PROJECT))
            return new PresentationFactory();
        else if(parentRuNode.getRuNodeType().equals(RuNodeType.PRESENTATION))
            return new SlideFactory();
        else if(parentRuNode.getRuNodeType().equals(RuNodeType.SLIDE))
            return new DummyFactory(ErrorTypes.WRONG_COMPONENT_SELECTED); //na slajd se nista ne dodaje i ovaj null treba regulisati za errore

        return null;
    }

     */
}
