package com.edx.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import com.edx.render.vector.Vector3;

public abstract class TESR<Tile extends TileEntity> extends TileEntitySpecialRenderer{
	protected final IModel model;
	
	protected TESR(IModel model){
		this.model = model;
	}
	
	public abstract void render(Tile tile, Vector3<Float> coords);
	
	@Override
	@SuppressWarnings("unchecked")
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f){
		this.render((Tile) tile, Vector3.of((float) x, (float) y, (float) z));
	}
}