package io.github.richardyin.empirecraft.common.entity.ai;

import java.lang.reflect.InvocationTargetException;
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
import net.minecraft.nbt.NBTTagCompound;

public abstract class NPCBehaviour {
	protected List<EntityAIBase> tasks = new ArrayList<>();
	protected List<EntityAIBase> targetTasks = new ArrayList<>();
	
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
	
	public static <T extends NPCBehaviour, E extends EntityCreature & IFactionEntity> T createFromNBT(NBTTagCompound compound, E entity, Class<T> c) throws ReflectiveOperationException {
			T behaviour = c.getConstructor(EntityCreature.class).newInstance(entity);
			behaviour.fillFromNBT(compound);
			return behaviour;
	}
	
	public abstract void fillFromNBT(NBTTagCompound compound);
	public abstract NBTTagCompound toNBT();
}
