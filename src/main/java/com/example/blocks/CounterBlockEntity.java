package com.example.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class CounterBlockEntity extends BlockEntity {
  public static void tick(Level level, BlockPos blockPos, BlockState blockState, CounterBlockEntity entity) {
    // entity.ticksSinceLast++;
  }

  private int clicks = 0;

  public int getClicks() {
    return this.clicks;
  }

  public void incrementClicks() {
    this.clicks++;
    this.setChanged();
  }

  public CounterBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlocksEntities.COUNTER_BLOCK_ENTITY, pos, state);
  }

  @Override
  protected void saveAdditional(ValueOutput output) {
    output.putInt("clicks", this.clicks);
    super.saveAdditional(output);
  }

  @Override
  protected void loadAdditional(ValueInput input) {
    super.loadAdditional(input);
    this.clicks = input.getIntOr("clicks", 0);
  }

  @Override
  public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
    return saveWithoutMetadata(registryLookup);
  }

  @Override
  public Packet<ClientGamePacketListener> getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }

  @Override
  public void setChanged() {
    super.setChanged();
    if (level == null)
      return;
    BlockState state = getBlockState();
    level.sendBlockUpdated(worldPosition, state, state, Block.UPDATE_ALL);
  }
}
