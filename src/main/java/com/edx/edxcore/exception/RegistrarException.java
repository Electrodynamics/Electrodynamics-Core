package com.edx.edxcore.exception;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

public final class RegistrarException extends Exception{
	private static final long serialVersionUID = 3700175574231364797L;
	
	public RegistrarException(Block b){
		super(String.format("Block class %s isn't a valid registrar class", b.getClass().getName()));
	}
	
	public RegistrarException(Item i){
		super(String.format("Item class %s isn't a valid registrar class", i.getClass().getName()));
	}
	
	public RegistrarException(TileEntity t){
		super(String.format("Tile class %s isn't a valid registrar class", t.getClass().getName()));
	}
}