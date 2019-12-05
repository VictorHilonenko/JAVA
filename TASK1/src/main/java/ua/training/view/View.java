package ua.training.view;

import java.util.Locale;
import java.util.ResourceBundle;

import ua.training.controller.ConfigSettings;
import ua.training.model.SlideShowGraphicFilesCollection;

public class View {
	
    // Resource Bundle Installation's
    static String MESSAGES_BUNDLE_NAME = "messages";
    public static final ResourceBundle bundle = ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME, 
    		ConfigSettings.USER_LOCALE);
    
    public void printMessage(String message) {
        System.out.println(message);
    }

	public void showSlideShow(SlideShowGraphicFilesCollection slideShowGraphicFilesCollection) {
		// TODO rendering will be here
		//don't forget to show total size of its files
		System.out.println("Slide show: "+slideShowGraphicFilesCollection.toString());
	}

}
