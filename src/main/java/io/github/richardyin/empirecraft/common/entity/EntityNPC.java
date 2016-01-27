package io.github.richardyin.empirecraft.common.entity;

import io.github.richardyin.empirecraft.npc.faction.Faction;
import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntityNPC extends EntityCreature {
	private Faction faction;
	public EntityNPC(World worldIn) {
		super(worldIn);
	}
	
	@Override
	public boolean canPickUpLoot() {
		return true;
	}
}
