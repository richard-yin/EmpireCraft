package io.github.richardyin.empirecraft.common.entity.ai;



public class PlayerFaction extends Faction {
	private String uuid;
	
	public PlayerFaction(String uuid, String name) {
		super(name);
		this.uuid = uuid;
	}
	
	public String getPlayerName() {
		//TODO figure out how to do this
		return "InsertNameHere";
	}
}
