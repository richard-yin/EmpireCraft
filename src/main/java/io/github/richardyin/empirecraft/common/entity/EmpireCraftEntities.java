package io.github.richardyin.empirecraft.common.entity;

import io.github.richardyin.empirecraft.EmpireCraft;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EmpireCraftEntities {
	private static Map<Class<? extends Entity>, String> names = new HashMap<Class<? extends Entity>, String>();
	
	private static List<Type> blacklistedTypes = Arrays.asList(
		Type.DEAD,
		Type.DENSE,
		Type.DRY,
		Type.MAGICAL,
		Type.MUSHROOM,
		Type.NETHER,
		Type.OCEAN,
		Type.SPOOKY,
		Type.WASTELAND
	);
	
	public static void initializeEntities() {
		//NPC
		EntityRegistry.registerModEntity(EntityNPC.class, "npc",
				EntityRegistry.findGlobalUniqueEntityId(), EmpireCraft.instance,
				512, 1, true, 0x333333, 0xcccccc);
		
		//events
		MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
	}
	
	public static void addSpawns() {
		List<BiomeGenBase> spawnableBiomes = new ArrayList<>();
		for(BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
			if(canBanditsSpawn(biome)) spawnableBiomes.add(biome);
		}
		EntityRegistry.addSpawn(EntityNPC.class, 1, 1, 1, EnumCreatureType.MONSTER,
				spawnableBiomes.toArray(new BiomeGenBase[0]));
	}
	
	private static boolean canBanditsSpawn(BiomeGenBase biome) {
		if(biome == null) return false;
		for(Type type : BiomeDictionary.getTypesForBiome(biome)) {
			if(blacklistedTypes.contains(type)) return false;
		}
		return true;
	}
}
