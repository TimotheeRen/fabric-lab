{
  pkgs,
  lib,
  config,
  inputs,
  ...
}:

{

  packages = with pkgs; [
    jdk25
    gradle_9
    python3

    # Graphical dependencies
    vulkan-loader
    libGL

    # Audio dependencies
    openal
    alsa-lib
    alsa-plugins
    libpulseaudio
    pipewire
  ];

  env.LD_LIBRARY_PATH =
    lib.makeLibraryPath [
      pkgs.vulkan-loader
      pkgs.libGL
      pkgs.openal
      pkgs.alsa-lib
      pkgs.alsa-plugins
      pkgs.libpulseaudio
      pkgs.stdenv.cc.cc.lib
    ] + ":/run/opengl-driver/lib";
}
