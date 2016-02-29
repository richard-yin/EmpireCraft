package io.github.richardyin.empirecraft.client.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlockRenders {
	public static void registerBlock(Block block, String name) {
		ModelResourceLocation itemModelResourceLocation =
				new ModelResourceLocation("empirecraft:" + name, "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
				Item.getItemFromBlock(block), 0, itemModelResourceLocation);
	}
}
