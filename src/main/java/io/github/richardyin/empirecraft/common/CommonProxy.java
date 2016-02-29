package io.github.richardyin.empirecraft.common;

import io.github.richardyin.empirecraft.common.block.EmpireCraftBlocks;
import io.github.richardyin.empirecraft.common.entity.EmpireCraftEntities;
import io.github.richardyin.empirecraft.common.item.EmpireCraftItems;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		config.save();
	}

	public void init(FMLInitializationEvent event) {
		EmpireCraftItems.initializeItems();
		EmpireCraftBlocks.initializeBlocks();
		EmpireCraftEntities.initializeEntities();
	}

	public void postInit(FMLPostInitializationEvent event) {
		EmpireCraftEntities.addSpawns();
	}
}
