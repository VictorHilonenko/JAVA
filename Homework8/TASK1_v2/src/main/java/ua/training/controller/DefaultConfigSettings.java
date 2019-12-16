package ua.training.controller;

/**
 * this class is for all settings of project
 */
public interface DefaultConfigSettings {
	/**
     * if INTERACTIVE_MODE is "true"
     * we'll let user to input initializing data manually
     * otherwise we'll emulate it programmatically
     */
	public static final boolean INTERACTIVE_MODE = false; //true; //
	
    /**
     * language setting
     */
	//public static final Locale USER_LOCALE = new Locale("en", "US");
	public static final String USER_LOCALE_LANGUAGE = "uk";
	public static final String USER_LOCALE_COUNTRY = "UA";
    
    /**
     * a real folder with some pictures
     */
	public static final String PATH_TO_FOLDER_WITH_GRAPHIC_FILES = "graphicFiles";
	
    /**
     * a range of sizes to filter
     */
	public static final long MIN_SIZE = 12500L;
	public static final long MAX_SIZE = 50000L;

    /**
     * a range of dates to filter
     */
	public static final long MIN_DATE_MODIFIED = 1575160669900L; //01.12.19 0:37:49
	public static final long MAX_DATE_MODIFIED = 1575171434830L; //01.12.19 3:37:14
	
    /**
     * a tag to filter
     */
	public static final String TAG_TO_FILTER = "signs";

	//now we use random values
	public static final String HOW_TO_FILTER = "1";
	
	//now we use random values
	public static final String HOW_TO_SORT = "1";

	public static final String DATE_FORMAT = "yyyy.MM.dd HH:mm:ss";
	
}
