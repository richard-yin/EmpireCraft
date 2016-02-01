package io.github.richardyin.empirecraft.common.entity.ai;

import io.github.richardyin.empirecraft.common.entity.ai.tasks.EntityNPCMeleeAttack;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;

public class NPCBehaviourMelee extends NPCBehaviour {

	public NPCBehaviourMelee(EntityCreature entity) {
		super(entity);
		for(int i = 0; i < NPCBehaviour.HOSTILE_MOBS.length; i++) {
			tasks.add(new EntityNPCMeleeAttack(
					entity, NPCBehaviour.HOSTILE_MOBS[i], 0.4f, false));
		}
		
		targetTasks.add(new EntityAIHurtByTarget(entity, true, NPCBehaviour.HOSTILE_MOBS));
		for(int i = 0; i < NPCBehaviour.HOSTILE_MOBS.length; i++) {
			targetTasks.add(new EntityAINearestAttackableTarget<EntityLivingBase>(
					entity, NPCBehaviour.HOSTILE_MOBS[i], true, true));
		}
	}
}