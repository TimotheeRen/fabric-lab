package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.items.ModItemIds;
import com.example.items.ModItems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;

public class Mod implements ModInitializer {
  public static final String MOD_ID = "example";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  @Override
  public void onInitialize() {
    LOGGER.info("Fabric Mod starting...");
    ItemComponentTooltipProviderRegistry.addAfter(DataComponents.DAMAGE, ModItemIds.CLICK_COUNT_COMPONENT);
    ServerLifecycleEvents.SERVER_STARTED.register(new ModServer());
    ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
      ServerPlayer player = handler.getPlayer();
      LOGGER.info(player.toString());
      /*
       * if (ModServer.bossEvent != null) {
       * ModServer.bossEvent.addPlayer(player);
       * }
       */
      // player.addPostEffect(Identifier.fromNamespaceAndPath("minecraft", "blur"));
    });
    ModItems.initialize();
  }
}
