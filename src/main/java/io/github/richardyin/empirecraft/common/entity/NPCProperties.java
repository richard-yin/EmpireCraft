package io.github.richardyin.empirecraft.common.entity;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class NPCProperties implements IExtendedEntityProperties {
	
	public static final String name = "NPCProperties";
	
	private final EntityNPC npc;
	private EntityNPC leader;

	public NPCProperties(EntityNPC npc) {
		this.npc = npc;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setLong("LeaderUUIDMost", leader.getUniqueID().getMostSignificantBits());
		properties.setLong("LeaderUUIDLeast", leader.getUniqueID().getLeastSignificantBits());
		compound.setTag(name, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		UUID id = new UUID(compound.getLong("LeaderUUIDMost"),
				compound.getLong("LeaderUUIDLeast"));

	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub

	}
}
