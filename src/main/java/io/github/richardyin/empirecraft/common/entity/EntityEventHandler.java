package io.github.richardyin.empirecraft.common.entity;

import io.github.richardyin.empirecraft.common.entity.ai.Faction;
import io.github.richardyin.empirecraft.common.entity.ai.NPCBehaviour;
import io.github.richardyin.empirecraft.common.entity.ai.NPCBehaviourMelee;
import io.github.richardyin.empirecraft.common.entity.ai.tasks.EntityNPCFollowLeader;
import io.github.richardyin.empirecraft.common.item.EmpireCraftItems;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityEventHandler {
	private static EntityEquipmentSlot[] ARMOR = {
		EntityEquipmentSlot.HEAD,
		EntityEquipmentSlot.CHEST,
		EntityEquipmentSlot.LEGS,
		EntityEquipmentSlot.FEET
	};
	
	@SubscribeEvent
	public void onMobSpawn(LivingSpawnEvent.CheckSpawn event) {
		if (event.getEntityLiving() instanceof EntityNPC) {
			if (!event.getWorld().canBlockSeeSky(new BlockPos(event.getEntity()))) {
				event.setResult(Result.DENY);
				return;
			}
			// bandit leader
			EntityNPC npc = (EntityNPC) event.getEntityLiving();
			npc.setFaction(Faction.BANDITS);
			npc.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.iron_sword));
			for (EntityEquipmentSlot slot : ARMOR) {
				npc.setItemStackToSlot(slot, getBlackArmor(slot));;
			}
			NPCBehaviour leaderBehaviour = new NPCBehaviourMelee(npc);
			leaderBehaviour.getTasks().add(new EntityAIWander(npc, 0.7f));
			npc.setBehaviour(leaderBehaviour);

			// bandit followers
			for (int i = 0; i < 3; i++) {
				EntityNPC follower = new EntityNPC(event.getWorld());
				follower.setPosition(event.getX(), event.getY(), event.getZ());
				follower.setItemStackToSlot(EntityEquipmentSlot.FEET, getBlackArmor(EntityEquipmentSlot.FEET));
				follower.setItemStackToSlot(EntityEquipmentSlot.CHEST, getBlackArmor(EntityEquipmentSlot.CHEST));
				follower.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(EmpireCraftItems.IRON_DAGGER));
				follower.setFaction(Faction.BANDITS);
				follower.setLeader(npc);

				NPCBehaviour followerBehaviour = new NPCBehaviourMelee(follower);
				followerBehaviour.getTasks().add(
						new EntityNPCFollowLeader(follower, 0.7, 2, 5));
				npc.setBehaviour(followerBehaviour);
			}
		}
	}

	private ItemStack getBlackArmor(EntityEquipmentSlot slot) {
		ItemArmor item = (ItemArmor) EntityLiving.func_184636_a(slot, 0);
		ItemStack stack = new ItemStack(item);
		item.setColor(stack, 0x000000);
		return stack;
	}
}
