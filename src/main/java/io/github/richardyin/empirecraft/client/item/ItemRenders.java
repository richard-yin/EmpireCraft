package io.github.richardyin.empirecraft.client.item;

import io.github.richardyin.empirecraft.common.item.weapon.ItemWeapon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemRenders {
	public static void registerItem(Item item, String name) {
		ModelResourceLocation itemModelResourceLocation =
				new ModelResourceLocation("empirecraft:" + name, "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				itemModelResourceLocation);
	}
}
