package ua.training.model.entities;

public enum FileExtensionsVector {
    SVG,
    EPS,
    PDF,
    AI,
    DXF;
    
	public static FileExtensionsVector getValueOf(String name) {
    	FileExtensionsVector res = null;
    	
    	for (FileExtensionsVector value : values()) {
    	    if(value.name().equals(name)) {
    	    	res = value;
    	    	break;
    	    }
    	}    	
    	return res;
    }
}
