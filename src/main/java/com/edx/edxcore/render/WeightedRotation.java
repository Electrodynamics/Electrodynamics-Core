package com.edx.edxcore.render;

import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import com.edx.edxcore.helpers.RotationHelper;
import com.edx.edxcore.render.vector.Vector3;

public final class WeightedRotation{
	private final float theta;
	private final Vector3<Float> mag;
	
	public WeightedRotation(float theta, Vector3<Float> mag){
		this.theta = theta;
		this.mag = mag;
	}
	
	public WeightedRotation(ForgeDirection dir, Vector3<Float> mag){
		this.theta = RotationHelper.INSTANCE.getTheta(dir);
		this.mag = mag;
	}
	
	public static WeightedRotation x(ForgeDirection dir){
		return new WeightedRotation(dir, RotationHelper.X_MAG);
	}
	
	public static WeightedRotation x(float theta){
		return new WeightedRotation(theta, RotationHelper.X_MAG);
	}
	
	public static WeightedRotation y(ForgeDirection dir){
		return new WeightedRotation(dir, RotationHelper.Y_MAG);
	}
	
	public static WeightedRotation y(float theta){
		return new WeightedRotation(theta, RotationHelper.Y_MAG);
	}
	
	public static WeightedRotation z(ForgeDirection dir){
		return new WeightedRotation(dir, RotationHelper.Z_MAG);
	}
	
	public static WeightedRotation z(float theta){
		return new WeightedRotation(theta, RotationHelper.Z_MAG);
	}
	
	public void rotate(){
		GL11.glRotatef(this.theta, this.mag.getX(), this.mag.getY(), this.mag.getZ());
	}
}