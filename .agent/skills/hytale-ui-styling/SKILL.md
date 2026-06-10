---
name: Hytale Custom UI Styling
description: Importing and using the Common UI styling library in Hytale custom UI to ensure cohesive design.
---

# Hytale Custom UI - Common Styling

Hytale provides a cohesive UI experience via shared styles and components in the `Common.ui` file. Re-using these ensures your custom UIs blend naturally with the core game's aesthetic.

## Accessing the Gallery

You can find a list of all available styles with live examples by running the `/ui-gallery` command in-game.

## Importing Common.ui

To use these styles in your UI file, you must first import the `Common.ui` document. 
The standard location within the Hytale pack is `Common/UI/Custom/Common.ui`.

If your custom UI document is in a subfolder of `Common/UI/Custom/` (e.g., `Common/UI/Custom/Template/MyPage.ui`), you must reference it using a relative path:

```hytale-ui
$Common = "../../Common.ui"; // Adjust relative path based on your folder depth

// Using styles:
TextButton {
    Style: $Common.@DefaultButtonStyle;
}

// Using components:
$Common.@Container { ... }
$Common.@TextButton { @Text = "My Button"; }
```

### Path Examples

| Your `.ui` File                              | Path to Import `Common.ui` |
|----------------------------------------------|----------------------------|
| `Common/UI/Custom/MyAwesomeMenu.ui`          | `$Common = "Common.ui";`   |
| `Common/UI/Custom/Menu/MyAwesomeMenu.ui`     | `$Common = "../Common.ui";` |
| `Common/UI/Custom/Menu/Popup/MyAwesomeMenu.ui`| `$Common = "../../Common.ui";` |
