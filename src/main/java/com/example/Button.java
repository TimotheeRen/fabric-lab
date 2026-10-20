package com.example;

import net.minecraft.network.chat.Component;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;

public class Button extends AbstractWidget {
  public Button(final int x, final int y, final int width, final int height, final Component message) {
    super(x, y, width, height, message);
  }

  @Override
  protected void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
    int color = isMouseOver(mouseX, mouseY) ? 0xFFFF0000 : 0xFF0000FF;
    graphics.fill(getX(), getY(), getX() + this.width, getY() + this.height, color);
  }

  @Override
  protected void updateWidgetNarration(NarrationElementOutput builder) {
    return;
  }
}
