package ActionManagers;

public class ActionManager {
    private NewAction newAction;
    private InfoAction infoAction;
    private NewRuNode newRuNode;
    private DeleteRuNode deleteRuNode;
    private EditPresentationAction editPresentationAction;
    private StartSlideShowAction startSlideShowAction;
    private EndSlideShowAction endSlideShowAction;
    private AddSlotAction addSlotAction;
    private RemoveSlotAction removeSlotAction;
    private MoveSlotAction moveSlotAction;
    private ChangeSlotColorAction changeSlotColorAction;
    private SelectSlotStateAction selectSlotStateAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private OpenSlotContentDialog openSlotContentDialog;
    private SharePresentationAction sharePresentationAction;
    private SaveAction saveAction;
    private OpenProjectAction openProjectAction;
    private SaveWorkspaceAction saveWorkspaceAction;
    private ImportPresentationAction importPresentationAction;
    private ExportPresentationAction exportPresentationAction;
    private OpenWorkspaceAction openWorkspaceAction;

    public ActionManager(){initialiseActions();}

    private void initialiseActions(){
        newAction = new NewAction();
        infoAction = new InfoAction();
        newRuNode = new NewRuNode();
        deleteRuNode = new DeleteRuNode();
        editPresentationAction = new EditPresentationAction();
        startSlideShowAction = new StartSlideShowAction();
        endSlideShowAction = new EndSlideShowAction();
        addSlotAction = new AddSlotAction();
        removeSlotAction = new RemoveSlotAction();
        moveSlotAction = new MoveSlotAction();
        changeSlotColorAction = new ChangeSlotColorAction();
        selectSlotStateAction = new SelectSlotStateAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        openSlotContentDialog = new OpenSlotContentDialog();
        sharePresentationAction = new SharePresentationAction();
        saveAction = new SaveAction();
        openProjectAction = new OpenProjectAction();
        saveWorkspaceAction = new SaveWorkspaceAction();
        importPresentationAction = new ImportPresentationAction();
        exportPresentationAction = new ExportPresentationAction();
        openWorkspaceAction = new OpenWorkspaceAction();
    }

    /** getters and setters */
    public NewAction getNewAction() {
        return newAction;
    }

    public void setNewAction(NewAction newAction) {
        this.newAction = newAction;
    }

    public InfoAction getInfoAction() {
        return infoAction;
    }

    public void setInfoAction(InfoAction infoAction) {
        this.infoAction = infoAction;
    }

    public NewRuNode getNewRuNode() {
        return newRuNode;
    }

    public void setNewRuNode(NewRuNode newRuNode) {
        this.newRuNode = newRuNode;
    }

    public DeleteRuNode getDeleteRuNode() {
        return deleteRuNode;
    }

    public void setDeleteRuNode(DeleteRuNode deleteRuNode) {
        this.deleteRuNode = deleteRuNode;
    }

    public EditPresentationAction getEditPresentationAction() {
        return editPresentationAction;
    }

    public void setEditPresentationAction(EditPresentationAction editPresentationAction) {
        this.editPresentationAction = editPresentationAction;
    }

    public StartSlideShowAction getStartSlideShowAction() {
        return startSlideShowAction;
    }

    public void setStartSlideShowAction(StartSlideShowAction startSlideShowAction) {
        this.startSlideShowAction = startSlideShowAction;
    }

    public EndSlideShowAction getEndSlideShowAction() {
        return endSlideShowAction;
    }

    public void setEndSlideShowAction(EndSlideShowAction endSlideShowAction) {
        this.endSlideShowAction = endSlideShowAction;
    }

    public AddSlotAction getAddSlotAction() {
        return addSlotAction;
    }

    public RemoveSlotAction getRemoveSlotAction() {
        return removeSlotAction;
    }

    public MoveSlotAction getMoveSlotAction() {
        return moveSlotAction;
    }

    public ChangeSlotColorAction getChangeSlotColorAction() {
        return changeSlotColorAction;
    }

    public SelectSlotStateAction getSelectSlotStateAction() {
        return selectSlotStateAction;
    }

    public void setSelectSlotStateAction(SelectSlotStateAction selectSlotStateAction) {
        this.selectSlotStateAction = selectSlotStateAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public OpenSlotContentDialog getOpenSlotContentDialog() {
        return openSlotContentDialog;
    }

    public SharePresentationAction getSharePresentationAction() {
        return sharePresentationAction;
    }

    public SaveAction getSaveAction() {
        return saveAction;
    }

    public OpenProjectAction getOpenProjectAction() {
        return openProjectAction;
    }

    public SaveWorkspaceAction getSaveWorkspaceAction() {
        return saveWorkspaceAction;
    }

    public ImportPresentationAction getImportPresentationAction() {
        return importPresentationAction;
    }

    public ExportPresentationAction getExportPresentationAction() {
        return exportPresentationAction;
    }

    public OpenWorkspaceAction getOpenWorkspaceAction() {
        return openWorkspaceAction;
    }
}
