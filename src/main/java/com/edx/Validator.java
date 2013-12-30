package com.edx;

import java.lang.annotation.Annotation;

public final class Validator{
	private Validator(){}
	
	public static boolean hasAnnotation(Class<? extends Annotation> annotation, Class<?> c){
		return c.isAnnotationPresent(annotation);
	}
}