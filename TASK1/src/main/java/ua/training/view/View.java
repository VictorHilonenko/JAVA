package ua.training.view;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import ua.training.ConfigSettings;
import ua.training.model.SlideShowGraphicFilesCollection;
import ua.training.model.entities.GraphicFile;

public class View {
	
    static String MESSAGES_BUNDLE_NAME = "messages";
    private static final ResourceBundle bundle = ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME, 
    		ConfigSettings.USER_LOCALE);
    
    public String getBundleString(String strName) {
    	String res = bundle.getString(strName);
    	
    	if(!"en".equals(ConfigSettings.USER_LOCALE.getDisplayLanguage())) {
    		try {
				res = new String(res.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	}
    	
    	return res;
    }
    
    public void printMessage(String message) {
		System.out.println(message);
    }

	public void showSlideShow(SlideShowGraphicFilesCollection slideShowGraphicFilesCollection) {
    	String strBytes = getBundleString(TextConstants.BYTES);
    	
		System.out.println("================================================");
    	printMessage(getBundleString(TextConstants.SLIDE_SHOW));
    	printMessage(getBundleString(TextConstants.NUMBER_OF_FILES)+" "+slideShowGraphicFilesCollection.getGraphicFilesList().size());
    	printMessage(getBundleString(TextConstants.TOTAL_SIZE_OF_FILES)+" "+slideShowGraphicFilesCollection.slideShowFilesTotalSize()+" "+strBytes);
    	printMessage(getBundleString(TextConstants.LIST_OF_FILES));
		
    	for(GraphicFile graphicFile :slideShowGraphicFilesCollection.getGraphicFilesList()) {
    		printMessage(graphicFile.getPathToFile()+" ("+graphicFile.getFileSize()+" "+strBytes+")"+graphicFile.getTags());
    	}
    	
		System.out.println("================================================");
		System.out.println("");
	}

}
