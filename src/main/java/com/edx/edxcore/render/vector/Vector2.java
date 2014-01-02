package com.edx.edxcore.render.vector;

public class Vector2<Type extends Number>{
	protected final Type x;
	protected final Type y;
	
	public Vector2(Type x, Type y){
		this.x = x;
		this.y = y;
	}
	
	public Type getX(){
		return this.x;
	}
	
	public Type getY(){
		return this.y;
	}
	
	public static <T extends Number> Vector2<T> of(T x, T y){
		return new Vector2<T>(x, y);
	}
	
	public static <T extends Number> Vector2<T> of(T... args){
		return new Vector2<T>(args[0], args[1]);
	}
}