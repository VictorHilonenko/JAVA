package ua.training.model.entities;

public class RasterGraphicFile extends GraphicFile {
    private FileExtensionsRaster fileExtension;
    private long widthPixels;
    private long heightPixels;
    
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
