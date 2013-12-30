package com.edx.exception;

import com.edx.allocator.Allocator;

public final class AllocationException extends Exception{
	private static final long serialVersionUID = 2075077401176559647L;
	
	public AllocationException(Allocator alloc, String tagName){
		super(String.format("Error allocating tag %s in allocator %s", tagName, alloc.getName()));
	}
	
	public AllocationException(Allocator alloc, Class<?> clazz){
		super(String.format("Error allocating class %s in allocator %s", clazz.getName(), alloc.getName()));
	}
}