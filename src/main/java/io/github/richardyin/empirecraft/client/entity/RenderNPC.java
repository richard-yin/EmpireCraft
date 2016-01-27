package io.github.richardyin.empirecraft.client.entity;

import io.github.richardyin.empirecraft.common.entity.EntityNPC;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerDeadmau5Head;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderNPC extends RenderBiped<EntityNPC> {
	private static final ModelPlayer model = new ModelPlayer(0, false);
	
	public RenderNPC(RenderManager renderManager) {
		super(renderManager, model, 0.5f);
		addLayer(new LayerBipedArmor(this));
		addLayer(new LayerHeldItem(this));
		addLayer(new LayerArrow(this));
		addLayer(new LayerCustomHead(getMainModel().bipedHead));
	}
	
	@Override
	public ModelPlayer getMainModel() {
		return model;
	}
	
	public static IRenderFactory<EntityNPC> getFactory() {
		return new IRenderFactory<EntityNPC>() {
			@Override
			public Render<? super EntityNPC> createRenderFor(
					RenderManager manager) {
				return new RenderNPC(manager);
			}
		};
	}
}
