package Data;

/**
 * The class to represent file info that we stored locally
 */
public class FileInfo {
    private String path;
    private boolean isActive;
    private String alias;

    public FileInfo(String path, boolean isActive, String alias) {
        this.path = path;
        this.isActive = isActive;
        this.alias = alias;
    }

    public String getPath() {
        return path;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getAlias() {
        return alias;
    }
}
