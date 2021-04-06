package mvc;

abstract public class Model extends Bean {
    private boolean unsavedChanges;
    private String fileName;

    public Model() {
        this.unsavedChanges = false;
        this.fileName = null;
    }

    public void changed() {
        this.unsavedChanges = true;
        firePropertyChange("Changed", false, true);
    }

    public void setUnsavedChanges(boolean b) {
        this.unsavedChanges = b;
    }

    public boolean getUnsavedChanges() {
        return this.unsavedChanges;
    }

    public void setFileName(String name) {
        this.fileName = name;
    }

    public String getFileName() {
        return this.fileName;
    }

}
