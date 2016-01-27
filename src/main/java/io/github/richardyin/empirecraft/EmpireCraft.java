package io.github.richardyin.empirecraft;

import io.github.richardyin.empirecraft.common.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

@Mod(modid = EmpireCraft.MODID, name = EmpireCraft.MODNAME, version=EmpireCraft.VERSION)
public class EmpireCraft {
	public static final String MODID = "EmpireCraft";
	public static final String MODNAME = "EmpireCraft";
	public static final String VERSION = "0.0.1";
	
	@Mod.Instance
	public static EmpireCraft instance;
	
	@SidedProxy(modId=MODID, clientSide="io.github.richardyin.empirecraft.client.ClientProxy",
			serverSide="io.github.richardyin.empirecraft.CommonProxy")
	public static CommonProxy proxy;
	
	public static Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		logger.info("Starting EmpireCraft");
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
