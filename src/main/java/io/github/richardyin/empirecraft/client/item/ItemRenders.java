package io.github.richardyin.empirecraft.client.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
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
