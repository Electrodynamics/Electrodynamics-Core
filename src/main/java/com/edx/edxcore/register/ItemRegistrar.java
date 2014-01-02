package com.edx.edxcore.register;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.edx.edxcore.Localizer;
import com.edx.edxcore.Validator;
import com.edx.edxcore.allocator.Allocator;
import com.edx.edxcore.annotation.Localize;
import com.edx.edxcore.annotation.Meta;
import com.edx.edxcore.exception.RegistrarException;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public final class ItemRegistrar{
	private final Localizer local;
	private final Mod mod;
	private final Allocator alloc;
	
	public ItemRegistrar(Localizer l, Class<?> c, Allocator alloc){
		if(Validator.hasAnnotation(Mod.class, c)){
			this.mod = c.getAnnotation(Mod.class);
			this.local = l;
			this.alloc = alloc;
		} else{
			throw new NullPointerException(String.format("Class %s needs to be a mod class", c.getName()));
		}
	}
	
	public void registerItem(Item i){
		try{
			Class<? extends Item> c = i.getClass();
			
			if(Validator.hasAnnotation(Localize.class, c)){
				Localize l = c.getAnnotation(Localize.class);
				
				if(Validator.hasAnnotation(Meta.class, c)){
					Meta m = c.getAnnotation(Meta.class);
					
					GameRegistry.registerItem(i, String.format("%s:%s", mod.modid(), l.value()));
					
					for(int x = 0; x < m.tags().length; x++){
						this.alloc.allocateRawMetaTag(this.alloc.getRootAllocationTag(c), m.tags()[x], x);
						LanguageRegistry.addName(new ItemStack(i, 1, x), this.local.translate(String.format("item.%s.%s.name", l.value(), m.tags()[x])));
					}
				} else{
					GameRegistry.registerItem(i, String.format("%s:%s", mod.modid(), l.value()));
					LanguageRegistry.addName(i, this.local.translate(String.format("item.%s.name", l.value())));
				}
			} else{
				throw new RegistrarException(i);
			}
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
}