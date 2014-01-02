package com.edx.edxcore.allocator;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import com.edx.edxcore.annotation.Allocate;
import com.edx.edxcore.exception.TagNotFoundException;

public final class Allocator{
	private final String name;
	private final MetaAllocator meta_alloc = new MetaAllocator(this);
	private final HashMap<String, Integer> ALLOCATION_CHART = new HashMap<String, Integer>();
	
	public Allocator(String name){
		this.name = name;
	}
	
	public MetaAllocator getMetaAllocator(){
		return this.meta_alloc;
	}
	
	public void allocateRawTag(String allocationTag, int value){
		this.ALLOCATION_CHART.put(allocationTag, value);
	}
	
	public void allocateRawMetaTag(String rootAllocationTag, String allocationTag, int value){
		this.getMetaAllocator().allocateMetaTag(rootAllocationTag, allocationTag, value);
	}
	
	public int getAllocatedValue(String tagName)
	throws TagNotFoundException{
		if(this.ALLOCATION_CHART.containsKey(tagName)){
			return this.ALLOCATION_CHART.get(tagName);
		} else{
			throw new TagNotFoundException(this, tagName);
		}
	}
	
	public String getRootAllocationTag(Class<?> clazz){
		try{
			Constructor<?> c = this.findAllocationConstructor(clazz);
			return c.getAnnotation(Allocate.class).value();
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	public <T> T generateAllocationInstance(Class<T> clazz){
		try {
			Constructor<T> c = this.findAllocationConstructor(clazz);
			c.setAccessible(true);
			return c.newInstance(this.getAllocatedValue(c.getAnnotation(Allocate.class).value()));
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	@SuppressWarnings("unused")
	private final <T> Constructor<T> findAllocationConstructor(Class<T> clazz){
		try{
			Constructor<T> c = clazz.getDeclaredConstructor(Integer.class);
			
			if(c.isAnnotationPresent(Allocate.class)){
				return c;
			} else{
				if(c != null){
					throw new IllegalStateException("Trying to register allocatable class without @Allocate above correct constructor (" + (String.valueOf(c.isAnnotationPresent(Allocate.class)) + ")"));
				}
			}
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
		
		throw new IllegalStateException("Couldn't find correct constructor for id injection");
	}
	
	public String getName(){
		return this.name;
	}
}
