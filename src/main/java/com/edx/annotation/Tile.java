package com.edx.annotation;

import com.edx.render.TESR;

public @interface Tile{
	String name();
	Class<? extends TESR<?>> tesr();
}