package ua.training.model.entities;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Random;
import java.util.StringJoiner;

import lombok.*;

@Getter
@Setter
public abstract class GraphicFile implements IGraphicFile {
    private String name;
    private String pathToFile;
    private long fileSize;
    private long lastModified;
    private HashSet<String> setOfTags;
    
	//TODO this array has to be removed after we get real tags from files now it is only for testing
    private static String arrayOfTags[] = {"humor", "signs", "road", "nature", "unusual"};
	//
    
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
    
	@Override
	public String name() {
		return name;
	}

	@Override
	public String pathToFile() {
		return pathToFile;
	}

	@Override
	public long fileSize() {
		return fileSize;
	}

	@Override
	public long lastModified() {
		return lastModified;
	}

	@Override
	public HashSet<String> setOfTags() {
		return setOfTags;
	}
    
	@Override
	public String firstTag() {
		return getFirstTag();
	}

	@Override
	public String lastModifiedAsString() {
		return getLastModifiedAsString();
	}

	@Override
	public String tags() {
		return getTags();
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

    public String getFirstTag() {
        return getTagByIndex(0);
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
	
	private static int getRandomNumber(int maxVal) {
        Random rand = new Random();
        return (int) rand.nextInt(maxVal);
	}
	
	private static String getRandomTag() {
		return arrayOfTags[getRandomNumber(arrayOfTags.length)];
	}
	
    public String getLastModifiedAsString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Date date = new Date(lastModified);
		return dateFormat.format(date);
    }

}
