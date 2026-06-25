---
name: Hytale Custom UI Layout
description: Understanding the Hytale Custom UI layout engine, positioning, Anchor, LayoutMode, and FlexWeight.
---

# Hytale Custom UI - Layout Engine

The layout system determines how UI elements are positioned and sized on screen.

## Core Properties

Every UI element layout is controlled by:
- **Anchor**: How the element positions/sizes itself within its container.
- **Padding**: Inner spacing that affects child layout.
- **LayoutMode**: How a container arranges its children.
- **FlexWeight**: How a child dynamically sizes based on remaining space.

---

## Anchor

`Anchor` defines the absolute or relative bounding box.
> **Note:** Hytale UI parser does NOT support percentage strings (e.g. `100%`). Use explicit values or `Full`.

- **Fixed Size**: `Anchor: (Width: 200, Height: 40);`
- **Positioning**: `Anchor: (Top: 10, Left: 20, Width: 100, Height: 30);`
- **Anchoring to Edges**: `Anchor: (Bottom: 10, Right: 10, Width: 100, Height: 30);`
- **Stretching**: 
  - Fill Container: `Anchor: (Top: 0, Bottom: 0, Left: 0, Right: 0);`
  - Shorthand: `Anchor: (Full: 0);` // 0 px margin on all sides.
- **Mixed**: `Anchor: (Top: 10, Bottom: 10, Left: 20, Width: 300);`

## Padding

`Padding` creates inner spacing inside containers.
- **Uniform**: `Padding: (Full: 20);`
- **Directional**: `Padding: (Top: 10, Bottom: 20, Left: 15, Right: 15);`
- **Shorthand**: `Padding: (Horizontal: 20, Vertical: 10);`

## LayoutMode

`LayoutMode` defines how a container lays out its children. When elements stack, you use their `Anchor` margins (e.g., `Anchor.Bottom` or `Anchor.Right`) as spacing.

- **Top**: Vertical stack, top to bottom.
- **Bottom**: Vertical stack, top to bottom, but aligned to the parent's bottom edge.
- **Left**: Horizontal stack, left to right.
- **Right**: Horizontal stack, left to right, but aligned to the parent's right edge.
- **Center**: Centers children horizontally.
- **Middle**: Centers children vertically.
- **CenterMiddle**: Horizontal stack, centered BOTH horizontally and vertically within the parent.
- **MiddleCenter**: Vertical stack, centered BOTH horizontally and vertically within the parent.
- **Full**: Absolute positioning via child `Anchor` properties.
- **LeftCenterWrap**: Horizontal stack that wraps to the next row when full, with rows centered.

### Scrolling Layouts
If content exceeds container height, add scrolling:
- **TopScrolling** / **BottomScrolling**
- **LeftScrolling** / **RightScrolling**
*(Don't forget to define a ScrollbarStyle, e.g., `ScrollbarStyle: $Common.@DefaultScrollbar;`)*

## FlexWeight

`FlexWeight` distributes remaining space among children after fixed widths are calculated.
```hytale-ui
Group {
    LayoutMode: Left;
    Anchor: (Width: 600);

    Group { FlexWeight: 1; } // 150px
    Group { FlexWeight: 2; } // 300px
    Group { FlexWeight: 1; } // 150px
}
```

## Visibility

```hytale-ui
Button {
    Visible: false;
}
```
Setting `Visible: false` removes the element from display AND layout calculation (it takes up no space).
