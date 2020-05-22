package com.testSpring.Blog.controllers.gitUtils.pojos;

public class FilesChangePojo {
    public String fileName;
    public String filePath;
    public int lineNumber;
    public LineStatus status;
    public String textOfChange;

    public FilesChangePojo(int lineNumber, String textOfChange, String fileName, String filePath) {
        this.lineNumber = lineNumber;
        this.textOfChange = textOfChange;
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
