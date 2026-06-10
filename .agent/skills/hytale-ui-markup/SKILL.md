---
name: Hytale Custom UI Markup
description: Rules and syntax for writing Hytale .ui markup files, including property types, templates, and named expressions.
---

# Hytale Custom UI - Markup Syntax

A UI document (`.ui` file) contains trees of elements. This file uses a custom CSS-like/JSON-like object syntax.

## Basic Syntax

Elements are declared by their Type, optionally followed by an ID (`#Name`), and a block of properties and children.

```hytale-ui
Group { Anchor: (Full: 10); }

Label #MyLabel {
  Style: LabelStyle(FontSize: 16);
  Text: "Hi! I am text.";
}

Group {
  LayoutMode: Left;
  Label { Text: "Child 1"; FlexWeight: 2; }
  Label { Text: "Child 2"; FlexWeight: 1; }
}
```

## Property Types

- **Boolean**: `Visible: true;`
- **Int**: `Height: 20;`
- **Float/Double**: `Min: 0.2;`
- **String**: `Text: "Hi!";`
- **Char**: `PasswordChar: "*";` (Max 1 char)
- **Array**: `TextSpans: [(Text: "Hi", IsBold: true)]`
- **Objects**: `Anchor: (Width: 20, Height: 10)`

### Translations
Translation keys can be referenced directly (using `%`) anywhere a string is expected:
```hytale-ui
Label { Text: %ui.general.cancel; }
```

### Colors
Colors use hex notation. `#rrggbb(a.a)` is preferred over `#rrggbbaa`.
```hytale-ui
Group { Background: #000000(0.3); } // Black with 30% opacity
Group { Background: #ffffff; } // Solid White
```

### Fonts and Paths
- **Fonts**: Handled automatically. Standard fonts are `"Default"`, `"Secondary"`, `"Mono"`.
- **Paths**: Defined relatively using strings. E.g., `Path: "Test.png"` or `Path: "../MyButton.png"`.

## Named Expressions and Variables

Named expressions are declared using `@` and scoped to their subtree. They must be at the top of the block.

```hytale-ui
@Title = "Hytale";
@BaseStyle = LabelStyle(FontSize: 24, LetterSpacing: 2);

Label {
  Text: @Title;
  // Use the spread operator '...' to reuse and override properties
  Style: (...@BaseStyle, FontSize: 36);
}
```

### Document References
You can reference another document and access its named expressions using `$`.

```hytale-ui
$Common = "../Common.ui";

TextButton {
  Style: $Common.@DefaultButtonStyle;
}
```

## Templates

You can declare a reusable piece of interface as a named expression. Local expressions inside templates can be overridden when instantiating.

```hytale-ui
// Define the template
@Row = Group {
  Anchor: (Height: 50);
  Label #Label { Anchor: (Left: 0, Width: 100); Text: @LabelText; }
  Group #Content { Anchor: (Left: 100); }
};

// Instantiate the template
@Row #MyFirstRow {
  @LabelText = "First row";
  #Content { TextInput {} } // Subscribing/adding children to the template
}
```
