package ua.training.model.entities;

import java.util.HashSet;

public interface IGraphicFile {
    String name();
    String pathToFile();
    long fileSize();
    long lastModified();
    HashSet<String> setOfTags();
    String firstTag();
    boolean hasTag(String tag);
    String lastModifiedAsString();
    String tags();
    
    static String getFileExtension(String absolutePath) {
		String res = "";
		
		int lastPoint = absolutePath.lastIndexOf(".");
		
		if(lastPoint > - 1) {
			res = absolutePath.substring(lastPoint+1);
		}
		
		return res;
    }    
    
}
