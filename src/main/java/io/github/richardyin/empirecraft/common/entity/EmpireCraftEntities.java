package io.github.richardyin.empirecraft.common.entity;

import io.github.richardyin.empirecraft.EmpireCraft;
import io.github.richardyin.empirecraft.client.entity.EntityRenders;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EmpireCraftEntities {
	private static Map<Class<? extends Entity>, String> names = new HashMap<Class<? extends Entity>, String>();
	
	public static void initializeEntities() {
		addEntity(EntityNPC.class, "npc");
	}
	
	public static void addEntity(Class<? extends Entity> entityClass, String name) {
		EntityRegistry.registerModEntity(entityClass, name,
				EntityRegistry.findGlobalUniqueEntityId(), EmpireCraft.instance,
				512, 1, true, 0x333333, 0xcccccc);
	}
}
