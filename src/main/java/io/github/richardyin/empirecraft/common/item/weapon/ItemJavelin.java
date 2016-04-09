package io.github.richardyin.empirecraft.common.item.weapon;

import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemJavelin extends ItemWeapon {

	public ItemJavelin(ToolMaterial material) {
		super(-1, -1.5f, 2, material);
		setMaxDamage(material.getMaxUses() / 2);
	}
}
