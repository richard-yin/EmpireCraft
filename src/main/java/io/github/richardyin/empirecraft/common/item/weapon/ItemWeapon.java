package io.github.richardyin.empirecraft.common.item.weapon;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class ItemWeapon extends ItemTool {

	protected ItemWeapon(float damageMod, ToolMaterial material,
			Set effectiveBlocks) {
		super(material.getDamageVsEntity() + damageMod, material, effectiveBlocks);
	}

	protected ItemWeapon(float damageMod, ToolMaterial material) {
		super(material.getDamageVsEntity() + damageMod, material, new HashSet<Block>());
	}
	
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(1, attacker);
        return true;
    }
	
	@Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
    {
        if ((double)blockIn.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(2, playerIn);
        }
        return true;
    }
}
