package io.github.richardyin.empirecraft.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class NPCProperties implements IExtendedEntityProperties {
	
	public static final String name = "NPCProperties";
	
	private final EntityNPC npc;

	public NPCProperties(EntityNPC npc) {
		this.npc = npc;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub

	}

}
