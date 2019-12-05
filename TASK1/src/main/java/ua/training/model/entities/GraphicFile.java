package ua.training.model.entities;

import java.util.HashSet;

public class GraphicFile {
    private String name;
    private String pathToFile;
    private long fileSize;
    private long lastModified;
    private HashSet<String> setOfTags;

    public GraphicFile() {
    	setOfTags = new HashSet<String>();
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public HashSet<String> getSetOfTags() {
        return setOfTags;
    }

    public void setSetOfTags(HashSet<String> setOfTags) {
        this.setOfTags = setOfTags;
    }

    public boolean hasTag(String tag) {
        return setOfTags.contains(tag);
    }

    public String getTagByIndex(int index) {
    	String res = "";
    	
    	if(index >= 0) {
    		if(setOfTags.size() > 0) {
    			res = (String) setOfTags.toArray()[index];
    		}
    	}
    	
        return res;
    }

	public void addTag(String tag) {
		setOfTags.add(tag);
	}

}
