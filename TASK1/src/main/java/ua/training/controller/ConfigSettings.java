package ua.training.controller;

import org.apache.commons.configuration.XMLConfiguration;

public class ConfigSettings {
    private XMLConfiguration configRead = null;

	public ConfigSettings() {
		try {
			configRead = new XMLConfiguration("settings.xml");
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	public boolean getInteractiveMode() {
		Boolean res = null;
		
		if(configRead != null) {
			res = configRead.getBoolean("INTERACTIVE_MODE");
		}
		
		if(res == null) {
			res = DefaultConfigSettings.INTERACTIVE_MODE;
		}
		
		return res;
	}
	
	public String getUserLocaleLanguage() {
		String res = null;
		
		if(configRead != null) {
			res = configRead.getString("USER_LOCALE_LANGUAGE");
		}
		
		if(res == null) {
			res = DefaultConfigSettings.USER_LOCALE_LANGUAGE;
		}
		
		return res;
	}
	
	public String getUserLocaleCountry() {
		String res = null;
		
		if(configRead != null) {
			res = configRead.getString("USER_LOCALE_COUNTRY");
		}
		
		if(res == null) {
			res = DefaultConfigSettings.USER_LOCALE_COUNTRY;
		}
		
		return res;
	}
	
	public String getPathToFolderWithGraphicFiles() {
		String res = null;
		
		if(configRead != null) {
			res = configRead.getString("PATH_TO_FOLDER_WITH_GRAPHIC_FILES");
		}
		
		if(res == null) {
			res = DefaultConfigSettings.PATH_TO_FOLDER_WITH_GRAPHIC_FILES;
		}
		
		return res;
	}
	
	public long getMinSize() {
		long res = 0L;
		
		if(configRead != null) {
			res = configRead.getLong("MIN_SIZE");
		}
		
		if(res == 0L) {
			res = DefaultConfigSettings.MIN_SIZE;
		}
		
		return res;
	}
	
	public long getMaxSize() {
		long res = 0L;
		
		if(configRead != null) {
			res = configRead.getLong("MAX_SIZE");
		}
		
		if(res == 0L) {
			res = DefaultConfigSettings.MAX_SIZE;
		}
		
		return res;
	}
	
	public long getMinDateModified() {
		long res = 0L;
		
		if(configRead != null) {
			res = configRead.getLong("MIN_DATE_MODIFIED");
		}
		
		if(res == 0L) {
			res = DefaultConfigSettings.MIN_DATE_MODIFIED;
		}
		
		return res;
	}
	
	public long getMaxDateModified() {
		long res = 0L;
		
		if(configRead != null) {
			res = configRead.getLong("MAX_DATE_MODIFIED");
		}
		
		if(res == 0L) {
			res = DefaultConfigSettings.MAX_DATE_MODIFIED;
		}
		
		return res;
	}
	
	public String getTagToFilter() {
		String res = null;
		
		if(configRead != null) {
			res = configRead.getString("TAG_TO_FILTER");
		}
		
		if(res == null) {
			res = DefaultConfigSettings.TAG_TO_FILTER;
		}
		
		return res;
	}
	
	public String getHowToFilter() {
		String res = null;
		
		if(configRead != null) {
			res = configRead.getString("HOW_TO_FILTER");
		}
		
		if(res == null) {
			res = DefaultConfigSettings.HOW_TO_FILTER;
		}
		
		return res;
	}
	
	public String getHowToSort() {
		String res = null;
		
		if(configRead != null) {
			res = configRead.getString("HOW_TO_SORT");
		}
		
		if(res == null) {
			res = DefaultConfigSettings.HOW_TO_SORT;
		}
		
		return res;
	}
	
}
