package io.github.richardyin.empirecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTab extends CreativeTabs {

	public ItemTab(int index, String label) {
		super(index, label);
	}

	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return EmpireCraftItems.IRON_SPEAR;
	}

}
