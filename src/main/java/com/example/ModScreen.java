
package com.example;

import java.util.Optional;

import com.mojang.renderpearl.api.pipeline.RenderPipeline;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class ModScreen extends Screen {

  private float totalTickProgress = 0.0F;
  private long lastFrameNanos = System.nanoTime();

  private static final Identifier WHITE_PIXEL = Identifier.fromNamespaceAndPath(
      "example",
      "textures/gui/white.png");

  private static final RenderPipeline ROUNDED_RECT = RenderPipelines.register(
      RenderPipeline.builder(
          RenderPipelines.GUI_TEXTURED_SNIPPET)
          .withLocation(
              Identifier.fromNamespaceAndPath(
                  "example",
                  "pipeline/rounded_rect"))
          .withVertexShader(
              Identifier.fromNamespaceAndPath(
                  "example",
                  "core/rounded_rect"))
          .withFragmentShader(
              Identifier.fromNamespaceAndPath(
                  "example",
                  "core/rounded_rect"))
          .build());

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

    int x = 20;
    int y = 20;
    int w = this.width - 40;
    int h = this.height - 40;

    if (w > 0 && h > 0) {
      graphics.blit(
          ROUNDED_RECT,
          WHITE_PIXEL,
          x, y,
          0.0F, 0.0F,
          w, h,
          w, h,
          0xAA101018);
    }
    Button button = new Button(this.width / 2 - 30, this.height / 2 - 15, 60, 30, Component.literal("Click me"));
    this.addRenderableWidget(button);
  }
}
