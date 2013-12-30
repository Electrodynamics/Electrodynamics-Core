package com.edx.helpers;

import org.lwjgl.opengl.GL11;

import com.edx.render.vector.Vector3;

public enum RenderHelper{
	INSTANCE;
	
	public void translate(Vector3<Float> vec){
		GL11.glTranslatef(vec.getX(), vec.getY(), vec.getZ());
	}
}