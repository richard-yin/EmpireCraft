package io.github.richardyin.empirecraft.common.entity.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;

public class NPCBehaviour {
	protected List<EntityAIBase> tasks = new ArrayList<EntityAIBase>();
	protected List<EntityAIBase> targetTasks = new ArrayList<EntityAIBase>();
	
	public static Class[] HOSTILE_MOBS =
		{EntityMob.class, EntitySlime.class, EntityGhast.class, EntityDragon.class};
	
	public <T extends EntityCreature & IFactionEntity> NPCBehaviour(T entity) {
		tasks.add(new EntityAISwimming(entity));
		tasks.add(new EntityAIRestrictOpenDoor(entity));
		tasks.add(new EntityAIOpenDoor(entity, true));
	}
	
	public List<EntityAIBase> getTasks() {
		return tasks;
	}

	public List<EntityAIBase> getTargetTasks() {
		return targetTasks;
	}
}
