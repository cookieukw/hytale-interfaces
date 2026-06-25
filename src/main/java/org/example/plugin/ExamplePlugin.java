package org.example.plugin;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import org.example.plugin.commands.TestUICommand;
import org.example.plugin.commands.InventoryCommand;
import org.example.plugin.commands.GuildCommand;
import org.example.plugin.commands.SkillTreeCommand;
import org.example.plugin.commands.BasicLayoutCommand;
import org.example.plugin.commands.NpcInteractionCommand;
import org.example.plugin.commands.CaskaraAdminCommand;
import org.example.plugin.commands.ShowcaseCommand;

import javax.annotation.Nonnull;

public class ExamplePlugin extends JavaPlugin {

        private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

        public ExamplePlugin(@Nonnull JavaPluginInit init) {
                super(init);
        }

        @Override
        protected void setup() {
                LOGGER.atInfo().log("Inicializando RuneCore...");
                this.getCommandRegistry().registerCommand(new TestUICommand());
                this.getCommandRegistry().registerCommand(new InventoryCommand());
                this.getCommandRegistry().registerCommand(new GuildCommand());
                this.getCommandRegistry().registerCommand(new SkillTreeCommand());
                this.getCommandRegistry().registerCommand(new BasicLayoutCommand());
                this.getCommandRegistry().registerCommand(new NpcInteractionCommand());
                this.getCommandRegistry().registerCommand(new CaskaraAdminCommand());
                this.getCommandRegistry().registerCommand(new ShowcaseCommand());
                LOGGER.atInfo().log("Template inicializado!");
        }
}