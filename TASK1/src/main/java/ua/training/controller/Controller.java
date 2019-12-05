package ua.training.controller;

import java.util.Scanner;

import ua.training.model.SlideShowGraphicFilesCollection;
import ua.training.view.TextConstants;
import ua.training.view.View;

/**
 * this is a Controller class of MVC pattern
 * 
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
    	
    	this.view.printMessage(view.bundle.getString(TextConstants.GREETING));
    	
        Scanner sc = getScannerInstance();
        
        String pathToFolderWithGraphicFiles = "";
        
        //* 1. to input path to folder
    	this.view.printMessage(view.bundle.getString(TextConstants.INPUT_PATH_TO_FOLDER_WITH_GRAPHIC_FILES));
        if(ConfigSettings.INTERACTIVE_MODE) {
	        if(sc.hasNextLine()) {
	        	pathToFolderWithGraphicFiles = sc.nextLine();
	        }
        } else {
        	pathToFolderWithGraphicFiles = ConfigSettings.PATH_TO_FOLDER_WITH_GRAPHIC_FILES;
        }
        
    	this.view.printMessage(view.bundle.getString(TextConstants.YOU_ENTERED));
    	this.view.printMessage(pathToFolderWithGraphicFiles);
        
    	//* 2. then we'll generate a SlideShow according to folder's contents
    	//* 3. then we'll show it with total size of it's files
		SlideShowGraphicFilesCollection slideShowGraphicFilesCollection = new SlideShowGraphicFilesCollection(pathToFolderWithGraphicFiles);
        
    	if(slideShowGraphicFilesCollection.getGraphicFilesList().size() == 0) {
			this.view.printMessage(view.bundle.getString(TextConstants.NOT_CREATED));
			return;
    	} else {
			this.view.printMessage(view.bundle.getString(TextConstants.WE_CREATED));
	    	this.view.showSlideShow(slideShowGraphicFilesCollection);
    	}
    	
    	//* 4. then we'll ask user to find (filter) all pics of SlideShow according to range of
    	//* 4.1. size
    	//* 4.2. time of last change
    	//* 4.3. tags
    	String howToFilter = "";
    	
    	this.view.printMessage(view.bundle.getString(TextConstants.INPUT_HOW_TO_FILTER));
        if(ConfigSettings.INTERACTIVE_MODE) {
	        if(sc.hasNextLine()) {
	        	howToFilter = sc.nextLine();
	        }
        } else {
        	howToFilter = ConfigSettings.HOW_TO_FILTER;
        }
    	
        SlideShowGraphicFilesCollection filteredSlideShow = null;
        
		if("1".equals(howToFilter)) { //1 - to filter files by size
			//TODO create interaction to take users input for these bounds:
			long minSize = ConfigSettings.MIN_SIZE;
			long maxSize = ConfigSettings.MAX_SIZE;
			
			filteredSlideShow = slideShowGraphicFilesCollection.filterGraphicFilesBySize(minSize, maxSize);
		} else if("2".equals(howToFilter)) { //2 - by last modified date
			//TODO create interaction to take users input for these bounds:
			long minDateModified = ConfigSettings.MIN_DATE_MODIFIED;
			long maxDateModified = ConfigSettings.MAX_DATE_MODIFIED;
			
			filteredSlideShow = slideShowGraphicFilesCollection.filterGraphicFilesByLastModified(minDateModified, maxDateModified);
		} else if("3".equals(howToFilter)) { //3 - by a tag:
			//TODO create interaction to take users input for a tag:
			String tag = ConfigSettings.TAG_TO_FILTER;
			
			filteredSlideShow = slideShowGraphicFilesCollection.filterGraphicFilesByTAGs(tag);
		} else {
	    	this.view.printMessage(view.bundle.getString(TextConstants.INCORRECT_INPUT));
	    	this.view.printMessage(view.bundle.getString(TextConstants.GOOD_BYE));
	    	return;
		}
		
   	 	//* 5. then we'll show that filtered collection
    	this.view.printMessage(view.bundle.getString(TextConstants.YOU_ENTERED));
    	this.view.printMessage(howToFilter);
		this.view.printMessage(view.bundle.getString(TextConstants.WE_CREATED));
    	this.view.showSlideShow(filteredSlideShow);
		
      	//* 6. then we'll ask user to choose a way how to sort that collection
    	String howToSort = "";
    	
    	this.view.printMessage(view.bundle.getString(TextConstants.INPUT_HOW_TO_SORT));
        if(ConfigSettings.INTERACTIVE_MODE) {
	        if(sc.hasNextLine()) {
	        	howToSort = sc.nextLine();
	        }
        } else {
        	howToSort = ConfigSettings.HOW_TO_SORT;
        }
    	
      	//* 7. then we sort it and show the result to user
		if("1".equals(howToSort)) { //1 - to filter files by size
			filteredSlideShow.sortGraphicFilesBySize();
		} else if("2".equals(howToSort)) { //2 - by last modified date
			filteredSlideShow.sortGraphicFilesByLastModified();
		} else if("3".equals(howToSort)) { //3 - by a tag:
			filteredSlideShow.sortGraphicFilesByTAGs();
		} else {
	    	this.view.printMessage(view.bundle.getString(TextConstants.INCORRECT_INPUT));
	    	this.view.printMessage(view.bundle.getString(TextConstants.GOOD_BYE));
	    	return;
		}
		
    	this.view.printMessage(view.bundle.getString(TextConstants.YOU_ENTERED));
    	this.view.printMessage(howToSort);
		this.view.printMessage(view.bundle.getString(TextConstants.RESULT_AFTER_SORTING));
    	this.view.showSlideShow(filteredSlideShow);
    	
    	this.view.printMessage(view.bundle.getString(TextConstants.GOOD_LUCK));
    }

}
