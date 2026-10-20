package com.example.items;

import java.util.function.Function;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorType;

public class ModItems {
  public static final Consumable POISON_FOOD_CONSUMABLE_COMPONENT = Consumables.defaultFood()
      .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 6 * 20, 1)))
      .build();
  public static final FoodProperties POISON_FOOD_COMPONENT = new FoodProperties.Builder().alwaysEdible().build();
  public static final Item SUSPICIOUS_SUBSTANCE = register(ModItemIds.SUSPICIOUS_SUBSTANCE, SuspiciousItem::new,
      new Item.Properties()
          .compostable(ResourceKey.create(Registries.CONTEXT_INT_PROVIDER,
              Identifier.fromNamespaceAndPath("example", "cooking/burn_time_poison")))
          .cookingFuel(
              ResourceKey.create(Registries.CONTEXT_INT_PROVIDER,
                  Identifier.fromNamespaceAndPath("example", "cooking/burn_time_poison")))
          .food(POISON_FOOD_COMPONENT, POISON_FOOD_CONSUMABLE_COMPONENT));

  public static final Item GUIDITE_SWORD = register(ModItemIds.GUIDITE_SWORD, Item::new,
      new Item.Properties().sword(ModItemIds.GUIDITE_TOOL_MATERIAL, 1f, 1f));

  public static final Item GUIDITE_HELMET = register(
      ModItemIds.GUIDITE_HELMET,
      Item::new,
      new Item.Properties().humanoidArmor(ModItemIds.GUIDITE_ARMOR_MATERIAL, ArmorType.HELMET)
          .durability(ArmorType.HELMET.getDurability(15)));
  public static final Item GUIDITE_CHESTPLATE = register(
      ModItemIds.GUIDITE_CHESTPLATE,
      Item::new,
      new Item.Properties().humanoidArmor(ModItemIds.GUIDITE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)
          .durability(ArmorType.CHESTPLATE.getDurability(15)));

  public static final Item GUIDITE_LEGGINGS = register(
      ModItemIds.GUIDITE_LEGGINGS,
      Item::new,
      new Item.Properties().humanoidArmor(ModItemIds.GUIDITE_ARMOR_MATERIAL, ArmorType.LEGGINGS)
          .durability(ArmorType.LEGGINGS.getDurability(15)));

  public static final Item GUIDITE_BOOTS = register(
      ModItemIds.GUIDITE_BOOTS,
      Item::new,
      new Item.Properties().humanoidArmor(ModItemIds.GUIDITE_ARMOR_MATERIAL, ArmorType.BOOTS)
          .durability(ArmorType.BOOTS.getDurability(15)));

  public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory,
      Item.Properties settings) {
    Item item = itemFactory.apply(settings.setId(itemKey));
    Registry.register(BuiltInRegistries.ITEM, itemKey, item);
    return item;
  }

  public static void initialize() {
    CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
        .register((creativeTab) -> creativeTab.accept(ModItems.SUSPICIOUS_SUBSTANCE));
    CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
        .register((creativeTab) -> creativeTab.accept(ModItems.GUIDITE_SWORD));
    CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
        .register((creativeTab) -> {
          creativeTab.accept(ModItems.GUIDITE_HELMET);
          creativeTab.accept(ModItems.GUIDITE_CHESTPLATE);
          creativeTab.accept(ModItems.GUIDITE_LEGGINGS);
          creativeTab.accept(ModItems.GUIDITE_BOOTS);
        });
  }
}
