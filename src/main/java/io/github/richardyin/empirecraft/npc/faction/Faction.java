package io.github.richardyin.empirecraft.npc.faction;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Faction {
	private String name;
	private Set<Faction> enemies;
	
	public Faction(String name) {
		this.name = name;
		enemies = new HashSet<Faction>();
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
	
	public boolean isEnemy(Faction other) {
		if(enemies.contains(other)) return true;
		if(other.getEnemies().contains(this)) return true;
		return false;
	}
	
	public void addEnemy(Faction other) {
		enemies.add(other);
	}
}
