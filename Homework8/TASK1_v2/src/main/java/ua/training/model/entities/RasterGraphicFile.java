package ua.training.model.entities;

import java.io.File;

import lombok.*;

@Getter
@Setter
public class RasterGraphicFile extends GraphicFile {
    private FileExtensionsRaster fileExtension;
    private long widthPixels;
    private long heightPixels;
    
	public RasterGraphicFile() {
		super();
	}

    public RasterGraphicFile(File file) {
       	super(file);
       	
    	//not completely DRY here
    	this.fileExtension = FileExtensionsRaster.getValueOf(IGraphicFile.getFileExtension(file.getAbsolutePath()).toUpperCase());
    	
    	//TODO determine width and height in future releases
    	this.setWidthPixels(100);
    	this.setHeightPixels(100);
	}

}
