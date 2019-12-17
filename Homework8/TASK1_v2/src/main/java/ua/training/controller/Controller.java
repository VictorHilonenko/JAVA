package ua.training.controller;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import ua.training.model.SlideShowCollection;
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
    private ConfigSettings configSettings;

    private Scanner scannerInstance = null;

    public Controller() {
    	configSettings = new ConfigSettings();
    	
        Locale userLocale = new Locale(configSettings.getUserLocaleLanguage(), configSettings.getUserLocaleCountry());
        
    	this.view = new View(userLocale);
    }

    private Scanner getScannerInstance() {
        if (scannerInstance == null) {
            scannerInstance = new Scanner(System.in);
        }
        return scannerInstance;
    }
    
    //* 1. to input path to folder
	//* 2. then we'll generate a SlideShow according to folder's contents
	//* 3. then we'll show it with total size of it's files
    private SlideShowCollection processInitSlideShow() {
    	SlideShowCollection res;
    	
        Scanner sc = getScannerInstance();
        
        String pathToFolderWithGraphicFiles = "";
        
    	view.printMessage(view.getBundleString(TextConstants.GREETING));
    	view.printMessage(view.getBundleString(TextConstants.INPUT_PATH_TO_FOLDER_WITH_GRAPHIC_FILES));
        if(configSettings.getInteractiveMode()) {
	        if(sc.hasNextLine()) {
	        	pathToFolderWithGraphicFiles = sc.nextLine();
	        }
        } else {
        	pathToFolderWithGraphicFiles = configSettings.getPathToFolderWithGraphicFiles();
        }
        
    	view.printMessage(view.getBundleString(TextConstants.YOU_ENTERED));
    	view.printMessage(pathToFolderWithGraphicFiles);
        
		SlideShowCollection slideShow = new SlideShowCollection(pathToFolderWithGraphicFiles);
        
    	if(slideShow.getGraphicFilesList().size() == 0) {
			view.printMessage(view.getBundleString(TextConstants.NOT_CREATED));
	    	throw new RuntimeException();
    	} else {
    		res = slideShow;
    	}
    	
    	return res;
    }
    
	//* 4. then we'll ask user to find (filter) all pics of SlideShow according to range of
	//* 4.1. size
	//* 4.2. time of last change
	//* 4.3. tags
    private SlideShowCollection processFileredSlideShow(SlideShowCollection slideShow) {
        SlideShowCollection filteredSlideShow;
        
    	String howToFilter = "";
    	
        Scanner sc = getScannerInstance();
        
    	view.printMessage(view.getBundleString(TextConstants.INPUT_HOW_TO_FILTER));
        if(configSettings.getInteractiveMode()) {
	        if(sc.hasNextLine()) {
	        	howToFilter = sc.nextLine();
	        }
        } else {
        	howToFilter = Integer.toString(getRandomNumber(3)); 
        	//howToFilter = ConfigSettings.HOW_TO_FILTER;
        }
    	
   	 	//* 5. then we'll show that filtered collection
    	view.printMessage(view.getBundleString(TextConstants.YOU_ENTERED));
    	view.printMessage(howToFilter);

		if("1".equals(howToFilter)) { //1 - to filter files by size
			//TODO create interaction to take users input for these bounds:
			long minSize = configSettings.getMinSize();
			long maxSize = configSettings.getMaxSize();
			
			filteredSlideShow = slideShow.filterBySize(minSize, maxSize);
		} else if("2".equals(howToFilter)) { //2 - by last modified date
			//TODO create interaction to take users input for these bounds:
			long minDateModified = configSettings.getMinDateModified();
			long maxDateModified = configSettings.getMaxDateModified();
			
			filteredSlideShow = slideShow.filterByLastModified(minDateModified, maxDateModified);
		} else if("3".equals(howToFilter)) { //3 - by a tag:
			//TODO create interaction to take users input for a tag:
			String tag = configSettings.getTagToFilter();
			
			filteredSlideShow = slideShow.filterByTAGs(tag);
		} else {
	    	view.printMessage(view.getBundleString(TextConstants.INCORRECT_INPUT));
	    	view.printMessage(view.getBundleString(TextConstants.GOOD_BYE));
	    	throw new RuntimeException();
		}
		
    	
    	return filteredSlideShow;
    }
    
  	//* 6. then we'll ask user to choose a way how to sort that collection
  	//* 7. then we sort it and show the result to user
    private SlideShowCollection processSortedSlideShow(SlideShowCollection slideShow) {
    	String howToSort = "";
    	
        Scanner sc = getScannerInstance();
        
    	view.printMessage(view.getBundleString(TextConstants.INPUT_HOW_TO_SORT));
        if(configSettings.getInteractiveMode()) {
	        if(sc.hasNextLine()) {
	        	howToSort = sc.nextLine();
	        }
        } else {
        	howToSort = Integer.toString(getRandomNumber(3)); 
        	//howToSort = ConfigSettings.HOW_TO_SORT;
        }
    	
    	view.printMessage(view.getBundleString(TextConstants.YOU_ENTERED));
    	view.printMessage(howToSort);
        
		if("1".equals(howToSort)) { //1 - to filter files by size
			slideShow.sortBySize();
		} else if("2".equals(howToSort)) { //2 - by last modified date
			slideShow.sortByLastModified();
		} else if("3".equals(howToSort)) { //3 - by a tag:
			slideShow.sortByTAGs();
		} else {
	    	view.printMessage(view.getBundleString(TextConstants.INCORRECT_INPUT));
	    	view.printMessage(view.getBundleString(TextConstants.GOOD_BYE));
	    	throw new RuntimeException();
		}
		
		return slideShow;
    }
        
    // Interaction
    public void processUserInput() {
    	SlideShowCollection slideShow = processInitSlideShow();
    	
		view.printMessage(view.getBundleString(TextConstants.WE_CREATED));
    	view.showSlideShow(slideShow);
    	
    	SlideShowCollection filteredSlideShow = processFileredSlideShow(slideShow);
    	
		view.printMessage(view.getBundleString(TextConstants.RESULT_AFTER_FILTERING));
    	view.showSlideShow(filteredSlideShow);
    	
    	SlideShowCollection sortedSlideShow = processSortedSlideShow(filteredSlideShow);
    	
		view.printMessage(view.getBundleString(TextConstants.RESULT_AFTER_SORTING));
    	view.showSlideShow(sortedSlideShow);
    	
    	view.printMessage(view.getBundleString(TextConstants.GOOD_LUCK));

    }
    
	private static int getRandomNumber(int maxVal) {
        Random rand = new Random();
        return (int) 1 + rand.nextInt(maxVal);
	}
}
