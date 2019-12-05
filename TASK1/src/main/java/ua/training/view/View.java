package ua.training.view;

import java.util.ResourceBundle;

import ua.training.controller.ConfigSettings;
import ua.training.model.SlideShowGraphicFilesCollection;
import ua.training.model.entities.GraphicFile;

public class View {
	
    // Resource Bundle Installation's
    static String MESSAGES_BUNDLE_NAME = "messages";
    public static final ResourceBundle bundle = ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME, 
    		ConfigSettings.USER_LOCALE);
    
    public void printMessage(String message) {
        System.out.println(message);
    }

	public void showSlideShow(SlideShowGraphicFilesCollection slideShowGraphicFilesCollection) {
		System.out.println("================================================");
    	printMessage(bundle.getString(TextConstants.SLIDE_SHOW));
    	printMessage(bundle.getString(TextConstants.NUMBER_OF_FILES)+" "+slideShowGraphicFilesCollection.getGraphicFilesList().size());
    	printMessage(bundle.getString(TextConstants.TOTAL_SIZE_OF_FILES)+" "+slideShowGraphicFilesCollection.slideShowFilesTotalSize());
    	printMessage(bundle.getString(TextConstants.LIST_OF_FILES));
		
    	String strBytes = bundle.getString(TextConstants.BYTES);
    	
    	for(GraphicFile graphicFile :slideShowGraphicFilesCollection.getGraphicFilesList()) {
    		printMessage(graphicFile.getPathToFile()+" ("+graphicFile.getFileSize()+" "+strBytes+")"+graphicFile.getTags());
    	}
    	
		System.out.println("================================================");
		System.out.println("");
	}

}
