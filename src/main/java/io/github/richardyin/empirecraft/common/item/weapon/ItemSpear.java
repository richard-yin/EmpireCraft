package io.github.richardyin.empirecraft.common.item.weapon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSpear extends ItemWeapon {
	public ItemSpear(ToolMaterial material) {
		super(1, -1, 3, material);
		setMaxDamage(material.getMaxUses() / 2);
	}
	
	public ItemSpear(float damageMod, float attackSpeed, double reach, ToolMaterial material) {
		super(damageMod, attackSpeed, reach, material);
		setMaxDamage(material.getMaxUses() / 2);
	}
}
