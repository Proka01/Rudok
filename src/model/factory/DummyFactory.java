package model.factory;

import errors.ErrorTypes;
import model.workspace.RuNode;

public class DummyFactory extends RuNodeFactory{

    private ErrorTypes errorType;

    public DummyFactory(ErrorTypes errorType) {
        this.errorType = errorType;
    }

    public DummyFactory() {
    }

    @Override
    public RuNode createModel(RuNode parentRuNode, int childCount) {
        return null;
    }

    public ErrorTypes getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorTypes errorType) {
        this.errorType = errorType;
    }
}
