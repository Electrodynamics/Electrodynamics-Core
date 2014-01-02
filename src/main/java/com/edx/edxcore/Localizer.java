package com.edx.edxcore;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Properties;

import cpw.mods.fml.common.Mod;

public abstract class Localizer{
	private final HashMap<String, Properties> LANG_MAP = new HashMap<String, Properties>();
	private final String LANG_DIR;
	
	protected Localizer(Class<?> modClazz){
		if(Validator.hasAnnotation(Mod.class, modClazz)){
			this.LANG_DIR = String.format("/assets/%s/lang", modClazz.getAnnotation(Mod.class).modid());
		} else{
			throw new IllegalStateException("Class " + modClazz.getName() + " isn't a mod class");
		}
	}
	
	public abstract String translate(String tag);
	
	protected void loadLanguage(String lang){
		InputStream stream = getClass().getResourceAsStream(String.format("%s/%s.properties", this.LANG_DIR, lang));
		
		try{
			if(stream != null){
				Reader reader = new InputStreamReader(stream);
				
				Properties props = new Properties();
				props.load(reader);
				this.LANG_MAP.put(lang, props);
				reader.close();
			} else{
				throw new NullPointerException("Cannot find stream for lang: " + lang);
			}
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	public String translate(String tag, String lang){
		if(this.LANG_MAP.containsKey(lang)){
			Properties props = this.LANG_MAP.get(lang);
			
			if(props.containsKey(tag)){
				return props.getProperty(tag);
			} else{
				throw new NullPointerException("Cannot find tag: " + tag);
			}
		} else{
			throw new NullPointerException("Cannot find lang: " + lang);
		}
	}
}