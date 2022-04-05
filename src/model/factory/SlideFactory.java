package model.factory;

import MVCObserver.mvc.SlideView;
import MVCObserver.mvc.SlideViewType;
import model.workspace.Presentation;
import model.workspace.RuNode;
import model.workspace.RuNodeType;
import model.workspace.Slide;

import java.awt.*;

public class SlideFactory extends RuNodeFactory{
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

        //Pravimo novi slajd
        Slide slide = new Slide(parentRuNode,"slide"+String.valueOf(childCount+1),childCount+1);
        slide.setRuNodeType(RuNodeType.SLIDE);

        //Pravimo 3 instance slideView-a koje ce biti u subscriberima od slajda
        SlideView slideView = new SlideView(slide,new Dimension(350,200), SlideViewType.SLIDE_VIEW);
        SlideView miniSlideView = new SlideView(slide, new Dimension(100,50), SlideViewType.MINI_SLIDE_VIEW);
        SlideView dummySlideView = new SlideView(slide,new Dimension(350,200), SlideViewType.DUMMY_SLIDE_VIEW);

        //Roditelju (Presentation) dodajemo novo dete (slide) u listu dece
        ((Presentation)parentRuNode).addChild(slide);
        slide.setParent((Presentation)parentRuNode);

        return slide;
    }
}
