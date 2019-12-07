package ua.training.model.entities;

import java.io.File;

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
    	this.fileExtension = FileExtensionsRaster.getValueOf(GraphicFile.getFileExtension(file.getAbsolutePath()).toUpperCase());
    	
    	//TODO determine width and height in future releases
    	this.setWidthPixels(100);
    	this.setHeightPixels(100);
	}

	public FileExtensionsRaster getFileExtension() {
		return fileExtension;
	}
	
	public void setFileExtension(FileExtensionsRaster fileExtension) {
		this.fileExtension = fileExtension;
	}

	public long getWidthPixels() {
		return widthPixels;
	}

	public void setWidthPixels(long widthPixels) {
		this.widthPixels = widthPixels;
	}

	public long getHeightPixels() {
		return heightPixels;
	}

	public void setHeightPixels(long heightPixels) {
		this.heightPixels = heightPixels;
	}

}
