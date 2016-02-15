package io.github.richardyin.empirecraft.common.item;

import io.github.richardyin.empirecraft.EmpireCraft;
import io.github.richardyin.empirecraft.client.item.ItemRenders;
import io.github.richardyin.empirecraft.common.item.weapon.ItemDagger;
import io.github.richardyin.empirecraft.common.item.weapon.ItemJavelin;
import io.github.richardyin.empirecraft.common.item.weapon.ItemSpear;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EmpireCraftItems {
	private static Map<Item, String> names = new HashMap<Item, String>();
	private static boolean client = false;
	
	private static ItemTab itemTab;
	
	public static Item IRON_SPEAR;
	public static Item IRON_JAVELIN;
	public static Item IRON_DAGGER;
	
	public static void setClientSide(boolean clientSide) {
		client = clientSide;
	}
	
	public static void initializeItems() {
		itemTab = new ItemTab(CreativeTabs.getNextID(), "EmpireCraftTab");
		IRON_SPEAR = addItem(new ItemSpear(ToolMaterial.IRON), "ironSpear");
		IRON_JAVELIN = addItem(new ItemJavelin(ToolMaterial.IRON), "ironJavelin");
		IRON_DAGGER = addItem(new ItemDagger(ToolMaterial.IRON), "ironDagger");
	}
	
	public static Item addItem(Item item, String name) {
		names.put(item, name);
		GameRegistry.registerItem(item, name);
		item.setUnlocalizedName(EmpireCraft.MODID + "_" + name);
		item.setCreativeTab(itemTab);
		if(client) ItemRenders.registerItem(item, name);
		return item;
	}
	
	public static String getName(Item item) {
		return names.get(item);
	}
}
