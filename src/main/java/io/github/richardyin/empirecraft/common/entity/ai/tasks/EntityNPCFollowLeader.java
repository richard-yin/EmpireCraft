package io.github.richardyin.empirecraft.common.entity.ai.tasks;

import io.github.richardyin.empirecraft.common.entity.EntityNPC;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * Copied from EntityAIFollowOwner and modified to work with NPCs
 * @author Richard
 *
 */
public class EntityNPCFollowLeader extends EntityAIBase
{
    private EntityNPC npc;
    private EntityLivingBase leader;
    private World world;
    private double followSpeed;
    private PathNavigate pathfinder;
    private int updateTick;
    float maxDist;
    float minDist;

    public EntityNPCFollowLeader(EntityNPC npcIn, double followSpeedIn, float minDistIn, float maxDistIn)
    {
        npc = npcIn;
        world = npcIn.worldObj;
        followSpeed = followSpeedIn;
        pathfinder = npcIn.getNavigator();
        minDist = minDistIn;
        maxDist = maxDistIn;
        setMutexBits(3);

        if (!(npcIn.getNavigator() instanceof PathNavigateGround))
        {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = npc.getLeader();

        if (entitylivingbase == null)
        {
            return false;
        }
        else if (entitylivingbase instanceof EntityPlayer && ((EntityPlayer)entitylivingbase).isSpectator())
        {
            return false;
        }
        else if (npc.getDistanceSqToEntity(entitylivingbase) < (double)(minDist * minDist))
        {
            return false;
        }
        else
        {
            leader = entitylivingbase;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !pathfinder.noPath() && npc.getDistanceSqToEntity(leader) > (double)(maxDist * maxDist);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        updateTick = 0;
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        leader = null;
        pathfinder.clearPathEntity();
    }

    private boolean func_181065_a(BlockPos p_181065_1_)
    {
        IBlockState iblockstate = world.getBlockState(p_181065_1_);
        Block block = iblockstate.getBlock();
        return block == Blocks.air ? true : !block.isFullCube(iblockstate);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
    	npc.getLookHelper().setLookPositionWithEntity(leader, 10.0F, (float)npc.getVerticalFaceSpeed());

    	if (--updateTick <= 0)
    	{
    		updateTick = 10;

    		if (!pathfinder.tryMoveToEntityLiving(leader, followSpeed))
    		{
    			if (!npc.getLeashed())
    			{
    				if (npc.getDistanceSqToEntity(leader) >= 144.0D)
    				{
    					int i = MathHelper.floor_double(leader.posX) - 2;
    					int j = MathHelper.floor_double(leader.posZ) - 2;
    					int k = MathHelper.floor_double(leader.getEntityBoundingBox().minY);

    					for (int l = 0; l <= 4; ++l)
    					{
    						for (int i1 = 0; i1 <= 4; ++i1)
    						{
//    							if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && World.doesBlockHaveSolidTopSurface(world, new BlockPos(i + l, k - 1, j + i1)) && func_181065_a(new BlockPos(i + l, k, j + i1)) && func_181065_a(new BlockPos(i + l, k + 1, j + i1)))
//    							{
//    								npc.setLocationAndAngles((double)((float)(i + l) + 0.5F), (double)k, (double)((float)(j + i1) + 0.5F), npc.rotationYaw, npc.rotationPitch);
//    								pathfinder.clearPathEntity();
//    								return;
//    							}
    						}
    					}
    				}
    			}
    		}
    	}
    }
}