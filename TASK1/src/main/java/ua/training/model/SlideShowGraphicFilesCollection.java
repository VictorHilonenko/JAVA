package ua.training.model;

import java.util.ArrayList;

import ua.training.model.entities.GraphicFile;

public class SlideShowGraphicFilesCollection {
    private ArrayList<GraphicFile> graphicFilesList;

    public SlideShowGraphicFilesCollection(String pathToFolder)  {
        this.graphicFilesList = new ArrayList<GraphicFile>(); 
        
        //factory pattern dependent on extension
        
        //TODO fill list
    }

	public SlideShowGraphicFilesCollection(ArrayList<GraphicFile> newGraphicFilesList) {
        this.graphicFilesList = new ArrayList<GraphicFile>(newGraphicFilesList); 
	}

	public ArrayList<GraphicFile> getGraphicFilesList() {
		return graphicFilesList;
	}

    public SlideShowGraphicFilesCollection filterGraphicFilesBySize(long minSize, long maxSize) {
        ArrayList<GraphicFile> newGraphicFilesList = new ArrayList<GraphicFile>();
        
        //TODO filter this collection and return the result in new instance of SlideShowGraphicFilesCollection
        
        return new SlideShowGraphicFilesCollection(newGraphicFilesList);
    }

    public SlideShowGraphicFilesCollection filterGraphicFilesByLastModified(long minDateModified, long maxDateModified) {
        ArrayList<GraphicFile> newGraphicFilesList = new ArrayList<GraphicFile>();
        
        //TODO filter this collection and return the result in new instance of SlideShowGraphicFilesCollection
        
        return new SlideShowGraphicFilesCollection(newGraphicFilesList);
    }

    public SlideShowGraphicFilesCollection filterGraphicFilesByTAGs(String... tags) {
        ArrayList<GraphicFile> newGraphicFilesList = new ArrayList<GraphicFile>();
        
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
    
    public long slideShowFilesTotalSize() {
        long res = 0;
    	
        //TODO count total size
        
        return res;
    }
    
}
