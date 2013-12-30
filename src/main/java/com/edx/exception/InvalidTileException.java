package com.edx.exception;

import net.minecraft.tileentity.TileEntity;

public final class InvalidTileException extends Exception {
	private static final long serialVersionUID = -778313929524879116L;

	public InvalidTileException(Class<? extends TileEntity> c){
		super(String.format("Tile class: %s requires @Tile to be registered", c.getName()));
	}
}