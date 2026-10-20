package com.example.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SuspiciousItem extends Item {
  public SuspiciousItem(Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResult use(Level level, Player user, InteractionHand hand) {
    BlockPos frontOfPlayer = user.blockPosition().relative(user.getDirection(), 10);
    LightningBolt lightningBolt = new LightningBolt(EntityTypes.LIGHTNING_BOLT, level);
    lightningBolt.setPos(new Vec3(frontOfPlayer));
    level.addFreshEntity(lightningBolt);
    return InteractionResult.SUCCESS;
  }
}
