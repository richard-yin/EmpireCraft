package io.github.richardyin.empirecraft;

import io.github.richardyin.empirecraft.item.EmpireCraftItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
	}

	public void init(FMLInitializationEvent event) {
		EmpireCraftItems.initializeItems();
	}

	public void postInit(FMLPostInitializationEvent event) {
		
	}

}
