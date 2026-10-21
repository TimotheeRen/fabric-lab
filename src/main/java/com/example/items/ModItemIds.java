package com.example.items;

import java.util.Map;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.block.Block;

public class ModItemIds {
  public static final ResourceKey<Item> SUSPICIOUS_SUBSTANCE = create("suspicious_substance");
  public static final TagKey<Block> INCORRECT_FOR_GUIDITE_TOOL = TagKey.create(Registries.BLOCK,
      Identifier.fromNamespaceAndPath("example", "incorrect_blocks"));
  public static final TagKey<Item> REPAIRS_GUIDITE_ARMOR = TagKey.create(BuiltInRegistries.ITEM.key(),
      Identifier.fromNamespaceAndPath("example", "repairs_guidite_armor"));
  public static final ToolMaterial GUIDITE_TOOL_MATERIAL = new ToolMaterial(INCORRECT_FOR_GUIDITE_TOOL, 455, 5.0F, 3F,
      22,
      REPAIRS_GUIDITE_ARMOR);
  public static final ResourceKey<Item> GUIDITE_SWORD = create("guidite_sword");

  public static final ResourceKey<Item> GUIDITE_HELMET = create("guidite_helmet");

  public static final ResourceKey<Item> GUIDITE_CHESTPLATE = create("guidite_chestplate");

  public static final ResourceKey<Item> GUIDITE_LEGGINGS = create("guidite_leggings");

  public static final ResourceKey<Item> GUIDITE_BOOTS = create("guidite_boots");

  public static final ResourceKey<EquipmentAsset> GUIDITE_ARMOR_MATERIAL_KEY = ResourceKey
      .create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath("example", "guidite"));

  public static final ArmorMaterial GUIDITE_ARMOR_MATERIAL = new ArmorMaterial(15, Map.of(
      ArmorType.HELMET, 3,
      ArmorType.CHESTPLATE, 8,
      ArmorType.LEGGINGS, 6,
      ArmorType.BOOTS, 3), 5,
      SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.5F, REPAIRS_GUIDITE_ARMOR, GUIDITE_ARMOR_MATERIAL_KEY);

  public static final DataComponentType<ComponentWithTooltip> CLICK_COUNT_COMPONENT = Registry
      .register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath("example", "click_count"),
          DataComponentType.<ComponentWithTooltip>builder().persistent(ComponentWithTooltip.CODEC)
              .networkSynchronized(ComponentWithTooltip.STREAM_CODEC).build());

  public static ResourceKey<Item> create(String name) {
    return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("example", name));
  }
}
