package com.edx.register;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.edx.Localizer;
import com.edx.Validator;
import com.edx.allocator.Allocator;
import com.edx.annotation.Localize;
import com.edx.annotation.Meta;
import com.edx.exception.RegistrarException;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public final class BlockRegistrar{
	private final Localizer local;
	private final Mod mod;
	private final Allocator alloc;
	
	public BlockRegistrar(Localizer l, Class<?> c, Allocator alloc){
		if(Validator.hasAnnotation(Mod.class, c)){
			this.mod = c.getAnnotation(Mod.class);
			this.local = l;
			this.alloc = alloc;
		} else{
			throw new NullPointerException(String.format("Class %s needs to be a mod class", c.getName()));
		}
	}
	
	public void registerBlock(Block b){
		try{
			Class<? extends Block> c = b.getClass();
			
			if(Validator.hasAnnotation(Localize.class, c)){
				Localize l = c.getAnnotation(Localize.class);
				
				if(Validator.hasAnnotation(Meta.class, c)){
					Meta m = c.getAnnotation(Meta.class);
					
					GameRegistry.registerBlock(b, m.itemBlock(), String.format("%s:%s", this.mod.modid(), l.value()));
					
					for(int i = 0; i < m.tags().length; i++){
						this.alloc.allocateRawMetaTag(this.alloc.getRootAllocationTag(c), m.tags()[i], i);
						LanguageRegistry.addName(new ItemStack(b, 1, i), this.local.translate("block." + l.value() + "." + m.tags()[i] + ".name"));
					}
				} else{
					GameRegistry.registerBlock(b, String.format("%s:%s", this.mod.modid(), l.value()));
					LanguageRegistry.addName(b, this.local.translate("block." + l.value() + ".name"));
				}
			} else{
				throw new RegistrarException(b);
			}
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
}