package ua.training.model.entities;

public class VectorGraphicFile extends GraphicFile {
    private FileExtensionsVector fileExtension;
    private boolean hasOpenPaths;
    
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
