package com.AxiusDesigns.AxiusPlugins.ConsoleBridge.Spigot.Events;

import com.AxiusDesigns.AxiusPlugins.ConsoleBridge.Spigot.ConsoleBridge;
import com.AxiusDesigns.AxiusPlugins.ConsoleBridge.Spigot.Utilities.ServerUtil;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.Console;

public class ChatEvents implements Listener {

    ConsoleBridge plugin;

    public ChatEvents(ConsoleBridge Instance) {
        this.plugin = Instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConsoleBridge:Chat");
        out.writeUTF(e.getMessage());
        e.getPlayer().sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConsoleBridge:Command");
        out.writeUTF(e.getMessage());
        e.getPlayer().sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent e) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConsoleBridge:Death");
        out.writeUTF(e.getDeathMessage());
        e.getEntity().sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
}
