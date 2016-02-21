package io.github.richardyin.empirecraft.common.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

public class NPCBehaviourSoldier extends NPCBehaviour {

	public <T extends EntityCreature & IFactionEntity> NPCBehaviourSoldier(
			T entity) {
		super(entity);
		
		targetTasks.add(new EntityNPCHurtByTarget(entity, true, EntityLivingBase.class));
		targetTasks.add(new EntityNPCNearestAttackableTarget(entity, true));
	}
}
