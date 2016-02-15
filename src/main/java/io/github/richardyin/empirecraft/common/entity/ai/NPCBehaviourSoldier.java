package io.github.richardyin.empirecraft.common.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityNPCHurtByTarget;

public class NPCBehaviourSoldier extends NPCBehaviour {

	public <T extends EntityCreature & IFactionEntity> NPCBehaviourSoldier(
			T entity) {
		super(entity);
		
		targetTasks.add(new EntityNPCHurtByTarget(entity, true, NPCBehaviour.HOSTILE_MOBS));
		for(int i = 0; i < NPCBehaviour.HOSTILE_MOBS.length; i++) {
			targetTasks.add(new EntityAINearestAttackableTarget<EntityLivingBase>(
					entity, NPCBehaviour.HOSTILE_MOBS[i], true, true));
		}
	}
}
