package net.minecraft.entity.ai;

import io.github.richardyin.empirecraft.common.entity.ai.Faction;
import io.github.richardyin.empirecraft.common.entity.ai.IFactionEntity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;

public class EntityNPCHurtByTarget extends EntityAIHurtByTarget {
	private Faction faction;

	public<T extends EntityCreature & IFactionEntity> EntityNPCHurtByTarget(T creatureIn,
			boolean entityCallsForHelpIn, Class... targetClassesIn) {
		super(creatureIn, entityCallsForHelpIn, targetClassesIn);
		faction = creatureIn.getFaction();
	}
	
	@Override
	protected boolean isSuitableTarget(EntityLivingBase target, boolean includeInvincibles) {
		if(!faction.isEnemy(target)) return false;
		return super.isSuitableTarget(target, includeInvincibles);
	}
}