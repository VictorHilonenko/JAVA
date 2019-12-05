package ua.training.model;

import java.io.File;
import java.util.ArrayList;

import ua.training.model.entities.VectorGraphicFile;
import ua.training.model.entities.FileExtensionsRaster;
import ua.training.model.entities.FileExtensionsVector;
import ua.training.model.entities.GraphicFile;
import ua.training.model.entities.RasterGraphicFile;

public class SlideShowGraphicFilesCollection {
    private ArrayList<GraphicFile> graphicFilesList;

    public SlideShowGraphicFilesCollection(String pathToFolder)  {
        this.graphicFilesList = new ArrayList<GraphicFile>(); 

    	if("".equals(pathToFolder)) {
    		return;
    	}
    	
		File myFolder = new File(pathToFolder);
		if(!myFolder.exists()) {
			//TODO throw exception in future releases
			return;
		}
		
		File[] files = myFolder.listFiles();
		
		if(files.length == 0) {
			return;
		}
		
		ArrayList<GraphicFile> availableGraphicFilesList = new ArrayList<GraphicFile>();
		
		for(File file: files) {
			
			GraphicFile graphicFile = null;
			
			String fileExtensionUpperCase = GraphicFile.getFileExtension(file.getAbsolutePath()).toUpperCase();
			
			if(FileExtensionsVector.getValueOf(fileExtensionUpperCase) != null) {
				graphicFile = new VectorGraphicFile(file);
			} else if(FileExtensionsRaster.getValueOf(fileExtensionUpperCase) != null) {
				graphicFile = new RasterGraphicFile(file);
			} else {
				//simply ignore unknown file extensions
				continue;
			}
			
			availableGraphicFilesList.add(graphicFile);
		}
    	
    	
        this.graphicFilesList.addAll(availableGraphicFilesList); 
    }

	public SlideShowGraphicFilesCollection(ArrayList<GraphicFile> newGraphicFilesList) {
        this.graphicFilesList = new ArrayList<GraphicFile>(newGraphicFilesList); 
	}

	public ArrayList<GraphicFile> getGraphicFilesList() {
		return graphicFilesList;
	}

    public SlideShowGraphicFilesCollection filterGraphicFilesBySize(long minSize, long maxSize) {
        if((minSize < 0) || (maxSize < 0)) {
        	//TODO maybe throw exception "invalid parameters" in future releases
        	return null;
        }
    	
        ArrayList<GraphicFile> newGraphicFilesList;
        if((minSize == 0) && (maxSize == 0)) {
        	newGraphicFilesList = graphicFilesList;
        } else {
        	newGraphicFilesList = new ArrayList<GraphicFile>();
        	for(GraphicFile graphicFile: graphicFilesList) {
        		long fileSize = graphicFile.getFileSize();
	        	if ((minSize > 0) && (maxSize == 0)) {
	        		if(fileSize >= minSize) {
	        			newGraphicFilesList.add(graphicFile);
	        		}
	            } else if ((minSize == 0) && (maxSize > 0)) {
	        		if(fileSize <= maxSize) {
	        			newGraphicFilesList.add(graphicFile);
	        		}
	            } else {
	        		if((minSize <= fileSize) && (fileSize <= maxSize)) {
	        			newGraphicFilesList.add(graphicFile);
	        		}
	            }
        	}
        }
        
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
    	
    	for(GraphicFile graphicFile: graphicFilesList) {
    		res += graphicFile.getFileSize();
    	}
        
        return res;
    }
    
}
