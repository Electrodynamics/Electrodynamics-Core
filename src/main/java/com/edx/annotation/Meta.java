package com.edx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.minecraft.item.ItemBlock;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Meta{
	String[] tags();
	Class<? extends ItemBlock> itemBlock() default ItemBlockDummy.class;
	
	public static final class ItemBlockDummy extends ItemBlock{
		public ItemBlockDummy(int id){
			super(id);
		}
	}
}