package io.github.richardyin.empirecraft.npc;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.EntityLiving;

public class EmpireCraftMobs {
	private static Map<Class<? extends EntityLiving>, String> names = new HashMap<>();
	private static boolean client = false;
	
	public static void setClientSide(boolean clientSide) {
		client = clientSide;
	}
	
	public static void initializeMobs() {
		
	}
	
	public static void addMob(Class<? extends EntityLiving> mobClass, String name) {
		
	}
}
