
package com.example;

import org.joml.Matrix3x2fStack;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

public class ModScreen extends Screen {

  private float totalTickProgress = 0.0F;
  private long lastFrameNanos = System.nanoTime();

  public ModScreen() {
    super(Component.literal("My screen"));

    Mod.LOGGER.info("Fabric GUI starting...");
  }

  @Override
  public void extractRenderState(
      GuiGraphicsExtractor graphics,
      int mouseX,
      int mouseY,
      float delta) {
    super.extractRenderState(graphics, mouseX, mouseY, delta);

    graphics.fill(
        0, 0, this.width, this.height,
        0xFF101018);

    graphics.text(
        this.font,
        "ModScreen is rendering",
        10, 10,
        0xFFFFFFFF,
        false);

    long now = System.nanoTime();

    float elapsedSeconds = (now - lastFrameNanos) / 1_000_000_000.0F;

    lastFrameNanos = now;

    totalTickProgress += elapsedSeconds * 20.0F;

    Matrix3x2fStack matrices = graphics.pose();

    matrices.pushMatrix();

    float scaleAmount = Mth.sin(totalTickProgress / 10.0F) / 2.0F + 1.5F;

    float rotationAmount = totalTickProgress / 50.0F;

    matrices.translate(
        this.width / 2.0F,
        this.height / 2.0F);

    matrices.rotate(rotationAmount);
    matrices.scale(scaleAmount, scaleAmount);
    matrices.translate(-20.0F, -20.0F);

    graphics.fill(
        0, 0,
        40, 40,
        0xFFFF5555);

    matrices.popMatrix();
  }
}
