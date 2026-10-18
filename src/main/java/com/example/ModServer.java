package com.example;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarted;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.BossEvent;

public class ModServer implements ServerStarted {
  public static final String MOD_ID = "example-server";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
  public static ServerBossEvent bossEvent;

  @Override
  public void onServerStarted(MinecraftServer server) {
    LOGGER.info("Fabric Server mod starting...");
    ServerBossEvent bossEvent = new ServerBossEvent(UUID.randomUUID(),
        Component.literal("Example mod"),
        BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS);
    bossEvent.setProgress(0.5F);
    ModServer.bossEvent = bossEvent;
  }
}
