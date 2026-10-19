#version 330
#extension GL_ARB_separate_shader_objects : require

#include <minecraft:dynamictransforms.glsl>

uniform sampler2D Sampler0;

layout(location = 0) in vec2 texCoord0;
layout(location = 1) in vec4 vertexColor;

layout(location = 0) out vec4 fragColor;

float roundedBoxSdf(
    vec2 p,
    vec2 halfSize,
    float radius
) {
    vec2 q = abs(p) - halfSize + vec2(radius);

    return length(max(q, vec2(0.0)))
        + min(max(q.x, q.y), 0.0)
        - radius;
}

void main() {
    vec2 uv = texCoord0;

    // Estimer la taille du rectangle en pixels.
    vec2 invPixel = vec2(
        abs(dFdx(uv.x)),
        abs(dFdy(uv.y))
    );

    vec2 sizePx = vec2(1.0)
        / max(invPixel, vec2(0.000001));

    // Recentrer les coordonnées sur le rectangle.
    vec2 p = (uv - vec2(0.5)) * sizePx;
    vec2 halfSize = sizePx * 0.5;

    // Rayon maximal de 8 pixels.
    float radius = min(
        8.0,
        min(halfSize.x, halfSize.y)
    );

    // Calculer la distance au bord arrondi.
    float dist = roundedBoxSdf(
        p,
        halfSize,
        radius
    );

    // Lisser les bords pour éviter les escaliers.
    float aa = max(fwidth(dist), 0.5);
    float alpha = 1.0
        - smoothstep(-aa, aa, dist);

    // Appliquer la couleur du rectangle.
    vec4 color = texture(Sampler0, uv)
        * vertexColor
        * ColorModulator;

    color.a *= alpha;

    if (color.a <= 0.001) {
        discard;
    }

    fragColor = color;
}
