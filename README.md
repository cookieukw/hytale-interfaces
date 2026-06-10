# Hytale Interfaces

This repository is dedicated to testing and building Custom UIs for Hytale. It contains test interfaces and provides an environment for experimenting with the Hytale Custom UI framework.

## 🤖 AI Agents Integration

To make it easier for AI assistants to build and understand Hytale interfaces, this project includes a specialized `.agent` folder. This folder contains various "skills" and context files (like layout rules, markup syntax, and styling guidelines) that help AIs generate accurate and high-quality UI code for Hytale.

## 🛠️ Available Commands

Below are the main commands used for managing and building the project:

### Gradle (Java)
- `./gradlew build` - Compiles the project and generates the binary files.
- `./gradlew jar` - Creates the `.jar` file of the mod and automatically triggers the deployment script.
- `./gradlew deploy` - Copies the compiled mod directly to the Hytale `UserData/Mods` folder. You can configure a custom Hytale directory by specifying the `hytale.dir` property in the `local.properties` file.

