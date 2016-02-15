package net.minecraft.entity.ai;

import com.google.common.base.Predicate;

import io.github.richardyin.empirecraft.common.entity.ai.Faction;
import io.github.richardyin.empirecraft.common.entity.ai.IFactionEntity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;

public class EntityNPCNearestAttackableTarget extends
		EntityAINearestAttackableTarget<EntityLivingBase> {

	public <T extends EntityCreature & IFactionEntity> EntityNPCNearestAttackableTarget(T creature,
			boolean checkSight) {
		super(creature, EntityLivingBase.class, 10, checkSight, false,
				getTargetSelector(creature.getFaction()));
	}

	private static Predicate<EntityLivingBase> getTargetSelector(final Faction faction) {
		return new Predicate<EntityLivingBase>() {
			@Override
			public boolean apply(EntityLivingBase input) {
				// do not prevent attacking if faction is null for some reason
				if(faction == null) return true;
				return faction.isEnemy(input);
			}
		};
	}

}
