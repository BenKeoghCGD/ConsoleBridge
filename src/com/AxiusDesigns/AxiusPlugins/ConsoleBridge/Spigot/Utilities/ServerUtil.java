package com.AxiusDesigns.AxiusPlugins.ConsoleBridge.Spigot.Utilities;

import com.AxiusDesigns.AxiusPlugins.ConsoleBridge.Spigot.ConsoleBridge;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ServerUtil {
    ConsoleBridge plugin;

    public ServerUtil(ConsoleBridge plugin) {
        this.plugin = plugin;
    }

    public void sendChatBungee(Player player, String message) {
    }
}

