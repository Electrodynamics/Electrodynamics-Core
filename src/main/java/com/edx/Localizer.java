package com.edx;

import java.util.HashMap;
import java.util.Properties;

public final class Localizer{
	private final HashMap<String, Properties> LANG_MAP = new HashMap<String, Properties>();
	private final String LANG_DIR;
	
	public Localizer(String modName){
		this.LANG_DIR = String.format("/assets/%s/lang", modName);
	}
	
	public String translate(String tag, String lang){
		if(this.LANG_MAP.containsKey(lang)){
			
		}
		
		return null;
	}
}