package ua.training.model;

import java.util.ArrayList;

public class SlideShowGraphicFilesCollection {
    private ArrayList graphicFilesList;

    public SlideShowGraphicFilesCollection(String pathToFolder)  {
        this.graphicFilesList = new ArrayList(); 
        
        //TODO fill list
    }

	public SlideShowGraphicFilesCollection(ArrayList newGraphicFilesList) {
		// TODO add this type of constructor
	}

	public ArrayList getGraphicFilesList() {
		return graphicFilesList;
	}

    public SlideShowGraphicFilesCollection filterGraphicFilesBySize(long minSize, long maxSize) {
        ArrayList newGraphicFilesList = new ArrayList();
        
        //TODO filter this collection and return the result in new instance of SlideShowGraphicFilesCollection
        
        return new SlideShowGraphicFilesCollection(newGraphicFilesList);
    }

    public SlideShowGraphicFilesCollection filterGraphicFilesByLastModified(long minDateModified, long maxDateModified) {
        ArrayList newGraphicFilesList = new ArrayList();
        
        //TODO filter this collection and return the result in new instance of SlideShowGraphicFilesCollection
        
        return new SlideShowGraphicFilesCollection(newGraphicFilesList);
    }

    public SlideShowGraphicFilesCollection filterGraphicFilesByTAGs(String... tags) {
        ArrayList newGraphicFilesList = new ArrayList();
        
        //TODO filter this collection and return the result in new instance of SlideShowGraphicFilesCollection
        
        return new SlideShowGraphicFilesCollection(newGraphicFilesList);
    }
    
    public void sortGraphicFilesBySize() {
    	//TODO sort it
    }

    public void sortGraphicFilesByLastModified() {
    	//TODO sort it
    }

    //sort by first TAG
    public void sortGraphicFilesByTAGs() {
    	//TODO sort it
    }
    
    
	
    
}