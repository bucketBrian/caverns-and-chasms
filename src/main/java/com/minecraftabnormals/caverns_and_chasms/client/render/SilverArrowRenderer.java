package com.minecraftabnormals.caverns_and_chasms.client.render;

import com.minecraftabnormals.caverns_and_chasms.common.entity.SilverArrowEntity;
import com.minecraftabnormals.caverns_and_chasms.core.CavernsAndChasms;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SilverArrowRenderer extends ArrowRenderer<SilverArrowEntity> {

	public SilverArrowRenderer(EntityRendererManager manager) {
		super(manager);
	}

	public ResourceLocation getEntityTexture(SilverArrowEntity entity) {
		return new ResourceLocation(CavernsAndChasms.MODID, "textures/entity/projectiles/silver_arrow.png");
	}
}