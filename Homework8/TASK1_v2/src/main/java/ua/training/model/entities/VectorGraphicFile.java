package ua.training.model.entities;

import java.io.File;

import lombok.*;

@Getter
@Setter
public class VectorGraphicFile extends GraphicFile {
    private FileExtensionsVector fileExtension;
    private boolean hasOpenPaths;
    
    public VectorGraphicFile() {
    	super();
	}

    public VectorGraphicFile(File file) {
    	super(file);
    	
    	//not completely DRY here
    	this.fileExtension = FileExtensionsVector.getValueOf(IGraphicFile.getFileExtension(file.getAbsolutePath()).toUpperCase());
    	
    	//TODO determine hasOpenPaths in future releases
    	this.hasOpenPaths = false;
	}
    
}
