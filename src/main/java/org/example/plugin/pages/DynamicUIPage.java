package org.example.plugin.pages;

import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.player.pages.BasicCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import javax.annotation.Nonnull;

public class DynamicUIPage extends BasicCustomUIPage {

    private final String uiFilePath;

    public DynamicUIPage(@Nonnull PlayerRef playerRef, @Nonnull String uiFilePath) {
        super(playerRef, CustomPageLifetime.CanDismiss);
        this.uiFilePath = uiFilePath;
    }

    @Override
    public void build(UICommandBuilder commandBuilder) {
        commandBuilder.append(uiFilePath);
    }
}
