package io.github.richardyin.empirecraft.common.item.weapon;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ItemWeapon extends ItemTool {
	private double reach = 0;

	protected ItemWeapon(float damageMod, ToolMaterial material, Set effectiveBlocks) {
		super(damageMod, 0f, material, effectiveBlocks);
	}

	protected ItemWeapon(float damageMod, ToolMaterial material, float attackSpeed) {
		super(damageMod, attackSpeed, material, new HashSet<Block>());
	}
	
	protected ItemWeapon(float damageMod, double reach, ToolMaterial material,
			Set effectiveBlocks) {
		super(damageMod, 0f, material, effectiveBlocks);
		this.reach = reach;
	}

	protected ItemWeapon(float damageMod, float attackSpeed, double reach, ToolMaterial material) {
		super(damageMod, attackSpeed, material, new HashSet<Block>());
		this.reach = reach;
	}
	
	public double getReach() {
		return reach;
	}
	
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(1, attacker);
        return true;
    }
	
	@Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos, EntityLivingBase entityLiving)
    {
        if ((double)blockIn.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(2, entityLiving);
        }

        return true;
    }
}
