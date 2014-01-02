package com.edx.edxcore.register;

import java.lang.reflect.Constructor;

import net.minecraft.tileentity.TileEntity;

import com.edx.edxcore.Validator;
import com.edx.edxcore.annotation.Tile;
import com.edx.edxcore.exception.InvalidTileException;
import com.edx.edxcore.render.TESR;

import cpw.mods.fml.client.registry.ClientRegistry;

public enum TileRegistrar{
	INSTANCE;
	
	public void registerTile(Class<? extends TileEntity> tile) throws InvalidTileException{
		if(Validator.hasAnnotation(Tile.class, tile)){
			ClientRegistry.registerTileEntity(tile, this.genTileID(tile), this.genTileRenderer(tile));
		} else{
			throw new InvalidTileException(tile);
		}
	}
	
	private TESR<?> genTileRenderer(Class<? extends TileEntity> tile) throws InvalidTileException{
		if(Validator.hasAnnotation(Tile.class, tile)){
			try{
				Constructor<? extends TESR<?>> c = tile.getAnnotation(Tile.class).tesr().getDeclaredConstructor();
				c.setAccessible(true);
				return c.newInstance();
			} catch(Exception ex){
				throw new RuntimeException(ex);
			}
		} else{
			throw new InvalidTileException(tile);
		}
	}
	
	public String genTileID(Class<? extends TileEntity> tile) throws InvalidTileException{
		if(Validator.hasAnnotation(Tile.class, tile)){
			return String.format("tile" + tile.getAnnotation(Tile.class).name());
		} else{
			throw new InvalidTileException(tile);
		}
	}
}