package com.edx.allocator;

import java.util.HashMap;

import com.edx.exception.TagNotFoundException;

public final class MetaAllocator{
	private final HashMap<String, HashMap<String, Integer>> META_ALLOCATION_CHART = new HashMap<String, HashMap<String, Integer>>();
	private final Allocator alloc;
	
	public MetaAllocator(Allocator alloc){
		this.alloc = alloc;
	}
	
	public int getMetaTag(String root, String name)
	throws TagNotFoundException{
		if(this.META_ALLOCATION_CHART.containsKey(root)){
			if(this.META_ALLOCATION_CHART.get(root).containsKey(name)){
				return this.META_ALLOCATION_CHART.get(root).get(name);
			} else{
				throw new TagNotFoundException(this.alloc, String.format("root(%s):tag(%s)", root, name));
			}
		} throw new TagNotFoundException(this.alloc, String.format("root(%s)", root));
	}
	
	public void allocateMetaTag(String root, String name, int value){
		if(!this.META_ALLOCATION_CHART.containsKey(root)){
			this.META_ALLOCATION_CHART.put(root, new HashMap<String, Integer>());
		}
		
		this.META_ALLOCATION_CHART.get(root).put(name, value);
	}
}