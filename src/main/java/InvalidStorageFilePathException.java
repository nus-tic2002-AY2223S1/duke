
public class InvalidStorageFilePathException extends Exception{
    public InvalidStorageFilePathException() {super("Error! File Path does not exist. Please check if " + StorageFile.homeDirectory() + "/duke/data/ file path exists.");}
}

