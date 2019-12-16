package ua.training.model.entities;

public enum FileExtensionsRaster {
    BMP,
    TIF,
    GIF,
    JPG;
	
	public static FileExtensionsRaster getValueOf(String name) {
		FileExtensionsRaster res = null;
    	
    	for (FileExtensionsRaster value : values()) {
    	    if(value.name().equals(name)) {
    	    	res = value;
    	    	break;
    	    }
    	}    	
    	return res;
    }
}
