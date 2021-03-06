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
import net.minecraft.nbt.NBTTagCompound;

public class NPCBehaviourMelee extends NPCBehaviourSoldier {

	public <T extends EntityCreature & IFactionEntity> NPCBehaviourMelee(T entity) {
		super(entity);
		tasks.add(new EntityNPCMeleeAttack(
				entity, EntityLivingBase.class, 0.4f, false));
	}

	@Override
	public void fillFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		// throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public NBTTagCompound toNBT() { 
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
