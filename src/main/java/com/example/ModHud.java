
package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

import org.joml.Matrix3x2fStack;

public class ModHud implements ClientModInitializer {
  private float totalTickProgress = 0.0F;

  @Override
  public void onInitializeClient() {
    Mod.LOGGER.info("Fabric Client starting...");
    HudElementRegistry.attachElementBefore(
        VanillaHudElements.CHAT,
        Identifier.fromNamespaceAndPath("example", "animated_hud"),
        this::render);
  }

  private void render(
      GuiGraphicsExtractor graphics,
      DeltaTracker deltaTracker) {
    totalTickProgress += deltaTracker.getGameTimeDeltaPartialTick(true);

    Matrix3x2fStack matrices = graphics.pose();
    matrices.pushMatrix();

    float scaleAmount = Mth.sin(totalTickProgress / 10.0F) / 2.0F + 1.5F;

    float rotationAmount = totalTickProgress / 50.0F;

    matrices.translate(60.0F, 60.0F);
    matrices.rotate(rotationAmount);
    matrices.scale(scaleAmount, scaleAmount);
    matrices.translate(-20.0F, -20.0F);

    graphics.fill(0, 0, 40, 40, 0xFFFF5555);

    matrices.popMatrix();
  }
}
