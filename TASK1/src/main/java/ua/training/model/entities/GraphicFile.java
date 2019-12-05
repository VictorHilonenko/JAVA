package ua.training.model.entities;

import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.StringJoiner;

public class GraphicFile {
    private String name;
    private String pathToFile;
    private long fileSize;
    private long lastModified;
    private HashSet<String> setOfTags;
    
	public static String getFileExtension(String absolutePath) {
		String res = "";
		
		int lastPoint = absolutePath.lastIndexOf(".");
		
		if(lastPoint > - 1) {
			res = absolutePath.substring(lastPoint+1);
		}
		
		return res;
	}

    public static String getFileNameWithoutExtension(String absolutePath) {
		String res = "";
		
		int lastSlash = absolutePath.lastIndexOf("\\");
		int lastPoint = absolutePath.lastIndexOf(".");
		
		if((lastPoint > - 1) && (lastSlash > -1)) {
			res = absolutePath.substring(lastSlash+1, lastPoint);
		}
		
		return res;
	}

    public GraphicFile() {
    	this.setOfTags = new HashSet<String>();
	}

    public GraphicFile(File file) {
    	setPathToFile(file.getAbsolutePath()); 
    	setName(getFileNameWithoutExtension(getPathToFile())); 
    	setFileSize(file.length());
		setLastModified(file.lastModified());
    	
    	this.setOfTags = new HashSet<String>();
    	
    	//TODO fill tags from file data in future releases, now random data:
		int numOfTags = getRandomNumber(arrayOfTags.length/2)+1;
		for(int i=0;i<=numOfTags-1;i++) {
			addTag(getRandomTag());
		}
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
	
    public String getTags() {
		StringJoiner sj = new StringJoiner(" #", " [#", "]");
    	
		for(String tag: setOfTags) {
			sj.add(tag);
		}
		
    	return sj.toString();
	}
	
	private static String arrayOfTags[] = {"humor", "signs", "road", "nature", "unusual"};
	
	private static int getRandomNumber(int maxVal) {
        Random rand = new Random();
        return (int) rand.nextInt(maxVal);
	}
	
	private static String getRandomTag() {
		return arrayOfTags[getRandomNumber(arrayOfTags.length-1)];
	}
	
}
