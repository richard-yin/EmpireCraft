package io.github.richardyin.empirecraft.common.entity;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.MapMaker;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class NPCProperties implements IExtendedEntityProperties {
	
	public static final String NAME = "NPCProperties";
	
	private final EntityNPC npc;
	private EntityNPC leader;

	public static final ConcurrentMap<UUID, EntityNPC> npcsByUuid = new MapMaker().
			weakValues().makeMap();

	public static final ConcurrentMap<UUID, EntityNPC> leaderReferences = new MapMaker().
			weakValues().makeMap();

	public static IExtendedEntityProperties get(Entity entity) {
		return entity.getExtendedProperties(NAME);
	}
	
	public static final void register(EntityNPC npc) {
		npc.registerExtendedProperties(NAME, new NPCProperties(npc));
	}

	public NPCProperties(EntityNPC npc) {
		this.npc = npc;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		if(leader == null) {
			properties.setLong("LeaderUUIDMost", 0);
			properties.setLong("LeaderUUIDLeast", 0);
		} else {
			properties.setLong("LeaderUUIDMost", leader.getUniqueID().getMostSignificantBits());
			properties.setLong("LeaderUUIDLeast", leader.getUniqueID().getLeastSignificantBits());
		}
		compound.setTag(NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		UUID id = new UUID(compound.getLong("LeaderUUIDMost"),
				compound.getLong("LeaderUUIDLeast"));
		if(id.getLeastSignificantBits() == 0 && id.getMostSignificantBits() == 0) return;
		leader = npcsByUuid.get(id);
		leaderReferences.put(id, npc);
	}

	@Override
	public void init(Entity entity, World world) {
	}

	public EntityNPC getLeader() {
		return leader;
	}

	public void setLeader(EntityNPC leader) {
		this.leader = leader;
	}

	public EntityNPC getNPC() {
		return npc;
	}
}
