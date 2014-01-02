package com.edx.edxcore.annotation;

import com.edx.edxcore.render.TESR;

public @interface Tile{
	String name();
	Class<? extends TESR<?>> tesr();
}