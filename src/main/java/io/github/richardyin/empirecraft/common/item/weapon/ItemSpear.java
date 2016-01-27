package io.github.richardyin.empirecraft.common.item.weapon;

import java.util.HashSet;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;

public class ItemSpear extends ItemWeapon {
	private ToolMaterial material;
	
	public ItemSpear(ToolMaterial material) {
		super(1, material);
		setMaxDamage(material.getMaxUses() / 2);
	}
}
