package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class Mod implements ModInitializer {
  public static final String MOD_ID = "example";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  @Override
  public void onInitialize() {
    LOGGER.info("Fabric Mod starting...");
    ServerLifecycleEvents.SERVER_STARTED.register(
        new ModServerStarted());
    ModItems.initialize();
  }
}
