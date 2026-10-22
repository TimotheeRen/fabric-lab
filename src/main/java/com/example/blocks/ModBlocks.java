package com.example.blocks;

import java.util.function.Function;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
  public static final Block CONDENSED_DIRT = register(BlockItemIds.CONDENSED_DIRT, Block::new,
      BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.GRASS));

  public static final Block CONDENSED_OAK_LOG = register(BlockItemIds.CONDENSED_OAK_LOG, RotatedPillarBlock::new,
      BlockBehaviour.Properties.of().sound(SoundType.WOOD));

  public static final Block PRISMARINE_LAMP = register(BlockItemIds.PRISMARINE_LAMP, PrismarineLampBlock::new,
      BlockBehaviour.Properties.of().sound(SoundType.LANTERN).lightLevel(PrismarineLampBlock::getLuminance));

  private static Block register(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> blockFactory,
      BlockBehaviour.Properties properties) {
    Block block = blockFactory.apply(properties.setId(id));
    return Registry.register(BuiltInRegistries.BLOCK, id, block);
  }

  private static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory,
      BlockBehaviour.Properties properties) {
    Block block = register(id.block(), blockFactory, properties);
    BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()));
    Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);
    return block;
  }

  public static void initialize() {
    CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((creativeTab) -> {
      creativeTab.accept(ModBlocks.CONDENSED_DIRT.asItem());
      creativeTab.accept(ModBlocks.CONDENSED_OAK_LOG.asItem());
      creativeTab.accept(ModBlocks.PRISMARINE_LAMP.asItem());
    });
  }
}
