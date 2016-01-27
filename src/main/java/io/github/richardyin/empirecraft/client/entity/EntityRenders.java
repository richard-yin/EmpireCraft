package io.github.richardyin.empirecraft.client.entity;

import io.github.richardyin.empirecraft.common.entity.EntityNPC;

import javax.swing.text.html.parser.Entity;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityRenders {
	public static void preInit() {
		RenderingRegistry.registerEntityRenderingHandler(EntityNPC.class, RenderNPC.getFactory());
	}
}
