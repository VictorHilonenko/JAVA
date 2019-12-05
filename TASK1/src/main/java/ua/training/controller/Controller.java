package ua.training.controller;

import java.util.Scanner;

import ua.training.model.SlideShowGraphicFilesCollection;
import ua.training.view.View;

/**
 * ConfigSettings interface has an option of controller's working mode
 * and some other options to emulate user's activity in automatic mode
 * 
 * here we will ask (or emulate user's answer according to a CONFIG constant) 
 * 1. to input path to folder
 * 2. then we'll generate a SlideShow according to folder's contents
 * 3. then we'll show it with total size of it's files
 * 4. then we'll ask user to find (filter) all pics of SlideShow according to range of
 * 4.1. size
 * 4.2. time of last change
 * 4.3. tags
 * 5. then we'll show that filtered collection
 * 6. then we'll ask user to choose a way how to sort that collection
 * 7. then we sort it and show the result to user
 */

public class Controller {
    private View view;

    private Scanner scannerInstance = null;

    public Controller(View view) {
        this.view = view;
    }

    private Scanner getScannerInstance() {
        if (scannerInstance == null) {
            scannerInstance = new Scanner(System.in);
        }
        return scannerInstance;
    }

    // Interaction
    public void processUserInput() {
        //this.view.printMessage(view.bundle.getString(TextConstants.GREETING));
        Scanner sc = getScannerInstance();
        
        String pathToFolderWithGraphicFiles = "";
        
        if(ConfigSettings.INTERACTIVE_MODE) {
	        if(sc.hasNextLine()) {
	        	pathToFolderWithGraphicFiles = sc.nextLine();
	        }
        } else {
        	pathToFolderWithGraphicFiles = ConfigSettings.PATH_TO_FOLDER_WITH_GRAPHIC_FILES;
        }
        
		SlideShowGraphicFilesCollection slideShowGraphicFilesCollection = new SlideShowGraphicFilesCollection(pathToFolderWithGraphicFiles);
        
		
    }

}
