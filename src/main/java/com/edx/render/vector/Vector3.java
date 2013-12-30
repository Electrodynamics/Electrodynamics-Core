package com.edx.render.vector;

public class Vector3<Type extends Number> extends Vector2<Type>{
	protected final Type z;
	
	public Vector3(Type x, Type y, Type z){
		super(x, y);
		this.z = z;
	}
	
	public Vector3(Vector2<Type> vec, Type z){
		this(vec.getX(), vec.getY(), z);
	}
	
	public Type getZ(){
		return this.z;
	}
	
	public static <T extends Number> Vector3<T> of(T x, T y, T z){
		return new Vector3<T>(x, y, z);
	}
	
	public static <T extends Number> Vector3<T> of(T... args){
		return new Vector3<T>(args[0], args[1], args[2]);
	}
}