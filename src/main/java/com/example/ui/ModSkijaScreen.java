
package com.example.ui;

import java.awt.Color;

import org.blackaddons.blackskija.api.Skija;
import org.blackaddons.blackskija.api.screen.SkijaScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class ModSkijaScreen extends SkijaScreen {
  public ModSkijaScreen() {
    super(Component.literal("Screen"));
  }

  @Override
  public void draw() {
    int w = 30, h = 20;
    int screenW = Minecraft.getInstance().getWindow().getGuiScaledWidth();
    int screenH = Minecraft.getInstance().getWindow().getGuiScaledHeight();

    Skija.INSTANCE.rect(
        (screenW - w) / 2,
        (screenH - h) / 2,
        w, h,
        new Color(90, 130, 255, 200));
    // Skija.INSTANCE.hollowRect(40, 40, 320, 200, 1.5, new Color(90, 130, 255,
    // 200), 14);
  }
}
