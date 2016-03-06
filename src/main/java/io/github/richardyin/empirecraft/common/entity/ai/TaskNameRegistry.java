package io.github.richardyin.empirecraft.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class TaskNameRegistry {
	private static BiMap<String, Class<? extends NPCBehaviour>> behaviours = HashBiMap.create();
	private static BiMap<String, Class<? extends EntityAIBase>> tasks = HashBiMap.create();
	
	public static void populateDefaultValues() {
		//TODO
	}
	
	public static void addBehaviour(String name, Class<? extends NPCBehaviour> c) {
		behaviours.put(name, c);
	}
	
	public static void addTask(String name, Class<? extends EntityAIBase> c) {
		tasks.put(name, c);
	}
	
	public static Class<? extends NPCBehaviour> getBehaviour(String name) {
		return behaviours.get(name);
	}
	
	public static Class<? extends EntityAIBase> getTask(String name) {
		return tasks.get(name);
	}
	
	public static String getBehaviourName(Class<? extends NPCBehaviour> c) {
		return behaviours.inverse().get(c);
	}
	
	public static String getTaskName(Class<? extends EntityAIBase> c) {
		return tasks.inverse().get(c);
	}
}
