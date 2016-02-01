package io.github.richardyin.empirecraft.common.entity.ai.tasks;

import io.github.richardyin.empirecraft.common.item.weapon.ItemWeapon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EntityNPCMeleeAttack extends EntityAIAttackOnCollide {

	public EntityNPCMeleeAttack(EntityCreature creature, double speedIn,
			boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
		// TODO Auto-generated constructor stub
	}

	public EntityNPCMeleeAttack(EntityCreature creature,
			Class<? extends Entity> targetClass, double speedIn,
			boolean useLongMemory) {
		super(creature, targetClass, speedIn, useLongMemory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Minimum distance at which the NPC can attack a target
	 */
	@Override
    protected double func_179512_a(EntityLivingBase attackTarget)
    {
		double reach = 0;
		ItemStack heldItem = attacker.getInventory()[0];
		if(heldItem != null && heldItem.getItem() instanceof ItemWeapon) {
			reach = ((ItemWeapon) heldItem.getItem()).getReach();
		}
        return super.func_179512_a(attackTarget) + reach * reach;
    }
}
