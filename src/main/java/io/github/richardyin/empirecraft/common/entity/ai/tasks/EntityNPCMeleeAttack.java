package io.github.richardyin.empirecraft.common.entity.ai.tasks;

import io.github.richardyin.empirecraft.common.item.weapon.ItemWeapon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Copied from EntityAIAttackOnCollide and modified to allow increased reach and more
 * frequent attacks. Subclassing was not an option due to private and package-private
 * fields.
 */
public class EntityNPCMeleeAttack extends EntityAIBase {
    private World world;
    protected EntityCreature attacker;
    /** An amount of decrementing ticks that allows the entity to attack once the tick reaches 0. */
    private int attackTick;
    /** The speed with which the mob will approach the target */
    private double speedTowardsTarget;
    /** When true, the mob will continue chasing its target, even if it can't find a path to them right now. */
    private boolean longMemory;
    /** The PathEntity of our entity. */
    private PathEntity entityPathEntity;
    private Class <? extends Entity > classTarget;
    private int delayCounter;
    private double targetX;
    private double targetY;
    private double targetZ;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;

    public EntityNPCMeleeAttack(EntityCreature creature, Class <? extends Entity > targetClass, double speedIn, boolean useLongMemory)
    {
        this(creature, speedIn, useLongMemory);
        this.classTarget = targetClass;
    }

    public EntityNPCMeleeAttack(EntityCreature creature, double speedIn, boolean useLongMemory)
    {
        this.attacker = creature;
        this.world = creature.worldObj;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

        if (entitylivingbase == null)
        {
            return false;
        }
        else if (!entitylivingbase.isEntityAlive())
        {
            return false;
        }
        else if (this.classTarget != null && !this.classTarget.isAssignableFrom(entitylivingbase.getClass()))
        {
            return false;
        }
        else
        {
            if (canPenalize)
            {
                if (--this.delayCounter <= 0)
                {
                    this.entityPathEntity = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
                    this.targetX = 4 + this.attacker.getRNG().nextInt(7);
                    return this.entityPathEntity != null;
                }
                else
                {
                    return true;
                }
            }
            this.entityPathEntity = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
            return this.entityPathEntity != null;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
        return entitylivingbase == null ? false : (!entitylivingbase.isEntityAlive() ? false : (!this.longMemory ? !this.attacker.getNavigator().noPath() : this.attacker.isWithinHomeDistanceFromPosition(new BlockPos(entitylivingbase))));
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.attacker.getNavigator().setPath(this.entityPathEntity, this.speedTowardsTarget);
        this.delayCounter = 0;
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.attacker.getNavigator().clearPathEntity();
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        EntityLivingBase target = this.attacker.getAttackTarget();
        this.attacker.getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);
        double distance = this.attacker.getDistanceSq(target.posX,
        		target.getEntityBoundingBox().minY, target.posZ);
        --this.delayCounter;

        if ((this.longMemory || this.attacker.getEntitySenses().canSee(target))
        		&& this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D
        		&& this.targetZ == 0.0D || target.getDistanceSq(this.targetX,
        				this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F))
        {
            this.targetX = target.posX;
            this.targetY = target.getEntityBoundingBox().minY;
            this.targetZ = target.posZ;
            this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);

            if (this.canPenalize)
            {
                this.targetX += failedPathFindingPenalty;
                if (this.attacker.getNavigator().getPath() != null)
                {
                    net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
                    if (finalPathPoint != null && target.getDistanceSq(finalPathPoint.xCoord, finalPathPoint.yCoord, finalPathPoint.zCoord) < 1)
                        failedPathFindingPenalty = 0;
                    else
                        failedPathFindingPenalty += 10;
                }
                else
                {
                    failedPathFindingPenalty += 10;
                }
            }

            if (distance > 1024.0D)
            {
                this.delayCounter += 10;
            }
            else if (distance > 256.0D)
            {
                this.delayCounter += 5;
            }

            if (!this.attacker.getNavigator().tryMoveToEntityLiving(target, this.speedTowardsTarget))
            {
                this.delayCounter += 15;
            }
        }

        this.attackTick --;

        if (distance <= getMinDistanceSq(target) && this.attackTick <= 0)
        {
            this.attackTick = 10;
               this.attacker.swingArm(EnumHand.MAIN_HAND);

            this.attacker.attackEntityAsMob(target);
        }
    }

    protected double getMinDistanceSq(EntityLivingBase attackTarget)
    {
    	double distance = attacker.width + attackTarget.width;
    	if(attacker.getHeldItem(EnumHand.MAIN_HAND) != null) {
    		Item heldItem = attacker.getHeldItem(EnumHand.MAIN_HAND).getItem();
    		if(heldItem instanceof ItemWeapon) 
    			distance += ((ItemWeapon) heldItem).getReach();
    		else if(heldItem.isItemTool(attacker.getHeldItem(EnumHand.MAIN_HAND)))
    			distance += 1;
    	}
        return Math.pow(distance, 2);
    }
}
