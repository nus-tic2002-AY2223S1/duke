package exceptions;

import storage.Storage;

public class InvalidStorageFilePathException extends Exception{
    public InvalidStorageFilePathException() {super("Error! File Path does not exist. Please check if " + Storage.homeDirectory() + "/duke/data/ file path exists.");}
}

