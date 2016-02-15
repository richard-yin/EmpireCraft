package io.github.richardyin.empirecraft.common.entity.ai;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class Faction {
	public static Faction BANDITS = new Faction("Bandits");
	
	private String name;
	private Set<Faction> enemies;
	private Set<Class<EntityLiving>> hostileMobs;
	
	public Faction(String name) {
		this.name = name;
		enemies = new HashSet<Faction>();
		hostileMobs = new HashSet<Class<EntityLiving>>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set getEnemies() {
		return Collections.unmodifiableSet(enemies);
	}
	
	public boolean isEnemy(EntityLivingBase other) {
		if(other instanceof IFactionEntity) {
			Faction faction = ((IFactionEntity) other).getFaction();
			if(this == faction) return false; // no faction is hostile to itself
			if(enemies.contains(faction) || faction.getEnemies().contains(this)) return true;
			if(faction == BANDITS) return true;
			return false;
		}
		if(this == BANDITS) {
			if(other instanceof IFactionEntity) return true; // bandits attack all other factions
			if(other instanceof EntityPlayer) return true;
			if(other instanceof EntityVillager) return true;
		}
		if(other instanceof IMob) return true; // all factions are hostile to mobs
		return false;
	}
	
	public void addEnemy(Faction other) {
		addEnemy(other, true);
	}
	
	public void addEnemy(Faction other, boolean addToOther) {
		enemies.add(other);
		if(addToOther) other.addEnemy(this, false);
	}
}
