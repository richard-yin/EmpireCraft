package io.github.richardyin.empirecraft.client;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import io.github.richardyin.empirecraft.client.entity.EntityRenders;
import io.github.richardyin.empirecraft.common.CommonProxy;
import io.github.richardyin.empirecraft.common.block.EmpireCraftBlocks;
import io.github.richardyin.empirecraft.common.entity.EmpireCraftEntities;
import io.github.richardyin.empirecraft.common.item.EmpireCraftItems;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		EmpireCraftItems.setClientSide(true);
		EmpireCraftBlocks.setClientSide(true);
		EntityRenders.preInit();
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}
