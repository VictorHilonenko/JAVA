package ua.training.model.entities;

import java.io.File;

public class VectorGraphicFile extends GraphicFile {
    private FileExtensionsVector fileExtension;
    private boolean hasOpenPaths;
    
    public VectorGraphicFile() {
    	super();
	}

    public VectorGraphicFile(File file) {
    	super(file);
    	
    	//not completely DRY here
    	this.fileExtension = FileExtensionsVector.getValueOf(GraphicFile.getFileExtension(file.getAbsolutePath()).toUpperCase());
    	
    	//TODO determine hasOpenPaths in future releases
    	this.hasOpenPaths = false;
	}

	public FileExtensionsVector getFileExtension() {
		return fileExtension;
	}
	
	public void setFileExtension(FileExtensionsVector fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	public boolean getHasOpenPaths() {
		return hasOpenPaths;
	}
	
	public void setHasOpenPaths(boolean hasOpenPaths) {
		this.hasOpenPaths = hasOpenPaths;
	}

}
