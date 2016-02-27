package io.github.richardyin.empirecraft.common.entity;

import io.github.richardyin.empirecraft.common.entity.ai.Faction;
import io.github.richardyin.empirecraft.common.entity.ai.IFactionEntity;
import io.github.richardyin.empirecraft.common.entity.ai.NPCBehaviour;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityNPC extends EntityCreature implements IFactionEntity {
	private Faction faction = Faction.BANDITS;
	private PathNavigateGround navigator;
	private NPCBehaviour behaviour;
	private EntityLivingBase leader;

	public EntityNPC(World worldIn) {
		super(worldIn);
		navigator = (PathNavigateGround) getNavigator();
		navigator.setCanSwim(true);
		navigator.setAvoidsWater(true);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
	}
	
	public void setBehaviour(NPCBehaviour behaviour) {
		this.behaviour = behaviour;
		tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
		int i = 0; // priority
		for(EntityAIBase task : behaviour.getTasks()) {
			tasks.addTask(i, task);
			i++;
		}
		i = 0;
		for(EntityAIBase task : behaviour.getTargetTasks()) {
			targetTasks.addTask(i, task);
			i++;
		}
	}
	
	/**
	 * Copied from EntityMob and refactored
	 */
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		Item heldItem = getHeldItem().getItem();
		
		float baseDamage = (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
		int knockback = 0;

		if (entityIn instanceof EntityLivingBase)
		{
			baseDamage += EnchantmentHelper.func_152377_a(this.getHeldItem(), ((EntityLivingBase)entityIn).getCreatureAttribute());
			knockback += EnchantmentHelper.getKnockbackModifier(this);
		}

		boolean success = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), baseDamage);

		if (success)
		{
			if (knockback > 0)
			{
				entityIn.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)knockback * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)knockback * 0.5F));
				this.motionX *= 0.6D;
				this.motionZ *= 0.6D;
			}

			int fireAspect = EnchantmentHelper.getFireAspectModifier(this);

			if (fireAspect > 0)
			{
				entityIn.setFire(fireAspect * 4);
			}

			applyEnchantments(this, entityIn);
		}

		return success;
	}
		
	@Override
	public boolean canPickUpLoot() {
		return true;
	}
	
	@Override
	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}

	public EntityLivingBase getLeader() {
		return leader;
	}

	public void setLeader(EntityLivingBase leader) {
		this.leader = leader;
	}
}
