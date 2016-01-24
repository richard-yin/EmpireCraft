package io.github.richardyin.empirecraft.item;

import io.github.richardyin.empirecraft.EmpireCraft;
import io.github.richardyin.empirecraft.item.weapon.ItemSpear;

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
	private static Map<Item, String> names = new HashMap<>();
	private static boolean client = false;
	
	private static ItemTab itemTab;
	
	public static Item IRON_SPEAR;
	
	public static void setClientSide(boolean clientSide) {
		client = clientSide;
	}
	
	public static void initializeItems() {
		itemTab = new ItemTab(CreativeTabs.getNextID(), "EmpireCraftTab");
		IRON_SPEAR = addItem(new ItemSpear(ToolMaterial.IRON), "ironSpear");
	}
	
	public static Item addItem(Item item, String name) {
		names.put(item, name);
		GameRegistry.registerItem(item, name);
		item.setUnlocalizedName(EmpireCraft.MODID + "_" + name);
		item.setCreativeTab(itemTab);
		if(client) addItemRender(item, name);
		return item;
	}
	
	@SideOnly(Side.CLIENT)
	private static void addItemRender(Item item, String name) {
		ModelResourceLocation itemModelResourceLocation =
				new ModelResourceLocation("empirecraft:" + name, "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				itemModelResourceLocation);
	}
	
	public static String getName(Item item) {
		return names.get(item);
	}
}
