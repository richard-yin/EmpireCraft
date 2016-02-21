package io.github.richardyin.empirecraft.common.entity;

import java.util.ArrayList;
import java.util.List;

import io.github.richardyin.empirecraft.EmpireCraft;
import io.github.richardyin.empirecraft.common.entity.ai.Faction;
import io.github.richardyin.empirecraft.common.entity.ai.NPCBehaviour;
import io.github.richardyin.empirecraft.common.entity.ai.NPCBehaviourMelee;
import io.github.richardyin.empirecraft.common.entity.ai.tasks.EntityNPCFollowLeader;
import io.github.richardyin.empirecraft.common.item.EmpireCraftItems;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityEventHandler {
	@SubscribeEvent
	public void onMobSpawn(LivingSpawnEvent.CheckSpawn event) {
		if(!event.world.canBlockSeeSky(new BlockPos(event.entity))) {
			event.setResult(Result.DENY);
			return;
		}
		if(event.entityLiving instanceof EntityNPC) {
			//bandit leader
			EntityNPC npc = (EntityNPC) event.entityLiving;
			npc.setFaction(Faction.BANDITS);
			npc.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
			for(int i = 1; i <= 4; i++) {
				npc.setCurrentItemOrArmor(i, getBlackArmor(i));
			}
			NPCBehaviour leaderBehaviour = new NPCBehaviourMelee(npc);
			leaderBehaviour.getTasks().add(new EntityAIWander(npc, 0.7f));
			npc.setBehaviour(leaderBehaviour);
			
			//bandit followers
			for(int i = 0; i < 3; i++) {
				EntityNPC follower = new EntityNPC(event.world);
				follower.setPosition(event.x, event.y, event.z);
				follower.setCurrentItemOrArmor(1, getBlackArmor(1));
				follower.setCurrentItemOrArmor(3, getBlackArmor(3));
				follower.setCurrentItemOrArmor(0, new ItemStack(EmpireCraftItems.IRON_DAGGER));
				follower.setFaction(Faction.BANDITS);
				follower.setLeader(npc);
				npc.getFollowers().add(follower);
				
				NPCBehaviour followerBehaviour = new NPCBehaviourMelee(follower);
				followerBehaviour.getTasks().add(new EntityNPCFollowLeader(follower, 0.7, 2, 5));
				npc.setBehaviour(followerBehaviour);
			}
		}
	}
	
	private ItemStack getBlackArmor(int slot) {
		ItemArmor item = (ItemArmor) EntityLiving.getArmorItemForSlot(slot, 0);
		ItemStack stack = new ItemStack(item);
		item.setColor(stack, 0x000000);
		return stack;
	}
}
