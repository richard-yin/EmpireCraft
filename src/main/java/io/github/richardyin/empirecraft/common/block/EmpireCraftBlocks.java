package io.github.richardyin.empirecraft.common.block;

import io.github.richardyin.empirecraft.EmpireCraft;
import io.github.richardyin.empirecraft.client.block.BlockRenders;
import io.github.richardyin.empirecraft.common.item.EmpireCraftItems;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EmpireCraftBlocks {
	private static Map<Block, String> names = new HashMap<>();
	private static boolean client = false;
	
	public static Block FIRE_BRICK;
	
	public static void setClientSide(boolean clientSide) {
		client = clientSide;
	}
	
	public static void initializeBlocks() {
		FIRE_BRICK = addBlock(new Block(Material.rock), "fireBrickBlock");
	}
	
	public static Block addBlock(Block block, String name) {
		names.put(block, name);
		GameRegistry.registerBlock(block, name);
		block.setUnlocalizedName(EmpireCraft.MODID + "_" + name);
		block.setCreativeTab(EmpireCraftItems.getItemTab());
		if(client) {
			BlockRenders.registerBlock(block, name);
		}
		return block;
	}
}
