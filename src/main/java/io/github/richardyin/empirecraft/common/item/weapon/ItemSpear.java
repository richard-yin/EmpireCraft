package io.github.richardyin.empirecraft.common.item.weapon;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSpear extends ItemWeapon {
	
	public ItemSpear(ToolMaterial material) {
		super(1, material);
		setMaxDamage(material.getMaxUses() / 2);
	}
}
