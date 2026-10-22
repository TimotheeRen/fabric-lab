package com.example.blocks;

import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

public class BlockItemIds {
  public static final BlockItemId CONDENSED_DIRT = create("condensed_dirt");
  public static final BlockItemId CONDENSED_OAK_LOG = create("condensed_oak_log");

  private static BlockItemId create(String name) {
    Identifier id = Identifier.fromNamespaceAndPath("example", name);
    return BlockItemId.create(id, id);
  }
}
