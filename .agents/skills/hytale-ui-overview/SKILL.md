---
name: Hytale Custom UI Overview
description: Overview of Hytale's Custom UI framework, architecture, and differences between Client and Server UI.
---

# Hytale Custom UI - Overview

## What is Custom UI?

Custom UI is Hytale's framework for creating **custom user interfaces** controlled by the game server. It allows modders to create interactive screens and HUD overlays through Java plugins and `.ui` asset files.

## Client UI vs Server (In-Game) UI

### Client UI (Not Moddable)
Built-in interfaces controlled by the C# game client. You cannot modify these.
- Main menu, settings, character creation.
- Built-in HUD (health, hotbar, chat).
- Inventory, crafting screens, and dev tools.

### In-Game UI (Moddable via Server)
Server-controlled interfaces that you can create and customize.
1. **Custom Pages**: Full-screen interactive overlays.
   - Can be dismissed by the player (ESC key).
   - Capture all input (keyboard and mouse).
   - Perfect for: shops, dialogs, menus, configuration screens.
2. **Custom HUDs**: Persistent overlay elements.
   - Display-only (no user interaction).
   - Always visible, lightweight, non-intrusive.
   - Perfect for: quest trackers, status displays, server info panels.

## Architecture

Server UI uses a **command-based and event-driven architecture**:

1. **Java Server** uses `UICommandBuilder` to build UI via commands (e.g., `append()`, `set()`, `clear()`).
2. Commands are sent to the C# Client.
3. **C# Client** parses `.ui` markup files and creates the visual elements (CustomPage or CustomHud).
4. The user interacts with the UI.
5. Events are sent back to the Java Server.
6. The Server processes the event in `handleDataEvent()` and sends updates back.

### Key Principles

- **Declarative, Not Imperative**: You don't instantiate UI objects in Java. You send commands describing what you want ("Set this label's text to 'Hello'").
- **Asset-Driven**: UI structure is defined in `.ui` markup files, not hardcoded in Java.
- **Event-Driven**: Interactions trigger events flowing to the server.
- **Selector-Based**: You target UI elements using selectors, similar to CSS:
  - `#MyButton`: Element with ID "MyButton".
  - `#List[0]`: First child of element "List".
  - `#List[0] #Title`: Element with ID "Title" in the first child of element "List".
  - `#Label.TextColor`: The TextColor property of element "Label".
