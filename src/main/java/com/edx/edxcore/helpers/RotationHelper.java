package com.edx.edxcore.helpers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ForgeDirection;

import com.edx.edxcore.render.vector.Vector3;

public enum RotationHelper {
	INSTANCE;
	
	public static final Vector3<Float> X_MAG = Vector3.of(1.0F, 0.0F, 0.0F);
	public static final Vector3<Float> Y_MAG = Vector3.of(0.0F, 1.0F, 0.0F);
	public static final Vector3<Float> Z_MAG = Vector3.of(0.0F, 0.0F, 1.0F);
	
	public ForgeDirection get3DRotation(EntityLivingBase living){
		switch(MathHelper.floor_double((double) (living.rotationYaw * 4.0 / 360.0F) + 0.5D) & 3)
		{
			case 0:{
				return ForgeDirection.EAST;
			}
			case 1:{
				return ForgeDirection.NORTH;
			}
			case 2:{
				return ForgeDirection.SOUTH;
			}
			case 3:{
				return ForgeDirection.WEST;
			}
			default:{
				return ForgeDirection.UNKNOWN;
			}
		}
	}
	
	public ForgeDirection loadNBTTagDirection(NBTTagCompound comp, ForgeDirection dir){
		return ForgeDirection.VALID_DIRECTIONS[comp.getByte("dir")];
	}
	
	public void setNBTTagDirection(NBTTagCompound comp, ForgeDirection dir){
		comp.setByte("dir", (byte) dir.ordinal());
	}
	
	public float getTheta(ForgeDirection dir){
		switch(dir)
		{
			case NORTH:{
				return 180.0F;
			}
			case WEST:{
				return 90.0F;
			}
			case EAST:{
				return 270.0F;
			}
			default:{
				return 0.0F;
			}
		}
	}
}