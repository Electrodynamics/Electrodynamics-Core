package com.edx.exception;

import com.edx.allocator.Allocator;

public final class TagNotFoundException extends Exception {
	private static final long serialVersionUID = -8688104416302569063L;
	
	public TagNotFoundException(Allocator alloc, String tagName){
		super(String.format("Tag %s not found in Allocator %s", tagName, alloc.getName()));
	}
}