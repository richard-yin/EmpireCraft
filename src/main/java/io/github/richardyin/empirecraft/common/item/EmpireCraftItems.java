package io.github.richardyin.empirecraft.common.item;

import io.github.richardyin.empirecraft.EmpireCraft;
import io.github.richardyin.empirecraft.client.item.ItemRenders;
import io.github.richardyin.empirecraft.common.block.EmpireCraftBlocks;
import io.github.richardyin.empirecraft.common.item.weapon.ItemDagger;
import io.github.richardyin.empirecraft.common.item.weapon.ItemJavelin;
import io.github.richardyin.empirecraft.common.item.weapon.ItemSpear;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EmpireCraftItems {
	private static Map<Item, String> names = new HashMap<>();
	private static boolean client = false;
	
	private static ItemTab itemTab;

	//Weapons/Tools
	public static Item IRON_PIKE;
	public static Item IRON_SPEAR;
	public static Item IRON_JAVELIN;
	public static Item IRON_DAGGER;
	
	//Other
	public static Item FIRE_CLAY;
	public static Item FIRE_BRICK;
	
	public static void setClientSide(boolean clientSide) {
		client = clientSide;
	}
	
	public static void initializeItems() {
		itemTab = new ItemTab(CreativeTabs.getNextID(), "EmpireCraftTab");
		IRON_PIKE = addItem(new ItemSpear(1, 5, ToolMaterial.IRON), "ironPike");
		IRON_SPEAR = addItem(new ItemSpear(ToolMaterial.IRON), "ironSpear");
		IRON_JAVELIN = addItem(new ItemJavelin(ToolMaterial.IRON), "ironJavelin");
		IRON_DAGGER = addItem(new ItemDagger(ToolMaterial.IRON), "ironDagger");
		FIRE_CLAY = addItem(new Item(), "fireClay");
		FIRE_BRICK = addItem(new Item(), "fireBrick");
	}
	
	public static void addRecipes() {
		ItemStack fireClay = new ItemStack(FIRE_CLAY, 8);
		ItemStack clay = new ItemStack(Items.clay_ball);
		ItemStack blazePowder = new ItemStack(Items.blaze_powder);
		
		// fire clay
		GameRegistry.addRecipe(fireClay,
				"CCC",
				"CBC",
				"CCC", 'C', clay, 'B', blazePowder);

		// fire brick
		ItemStack fireBrick = new ItemStack(FIRE_BRICK);
		fireClay = new ItemStack(FIRE_CLAY);
		GameRegistry.addSmelting(fireClay, fireBrick, 0.1f);

		// fire brick block
		ItemStack fireBrickBlock = new ItemStack(EmpireCraftBlocks.FIRE_BRICK);
		GameRegistry.addRecipe(fireBrickBlock, "BB", "BB", 'B', fireBrick);
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
	
	public static ItemTab getItemTab() {
		return itemTab;
	}
}
