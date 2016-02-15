package io.github.richardyin.empirecraft.common.item.weapon;

import java.util.Set;

import net.minecraft.item.Item.ToolMaterial;

public class ItemDagger extends ItemWeapon {
	
	public ItemDagger(ToolMaterial material) {
		super(-1, 0, material);
		setMaxDamage(material.getMaxUses() / 2);
	}

}
