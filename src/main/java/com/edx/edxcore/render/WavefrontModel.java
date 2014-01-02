package com.edx.edxcore.render;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public final class WavefrontModel implements IModel{
	private final IModelCustom model;
	
	public WavefrontModel(String modId, String modelName){
		this.model = AdvancedModelLoader.loadModel(String.format("%s:models/%s.obj", modId, modelName));
	}
	
	@Override
	public void render() {
		this.model.renderAll();
	}

	@Override
	public void renderPart(String part) {
		this.model.renderPart(part);
	}
	
	@Override public void render(float f5){}
}