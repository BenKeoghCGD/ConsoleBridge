package com.AxiusDesigns.AxiusPlugins.ConsoleBridge.Bungeecord.Events;

import com.AxiusDesigns.AxiusPlugins.ConsoleBridge.Bungeecord.ConsoleBridge;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.UUID;

public class ChannelListener implements Listener {

    ConsoleBridge plugin;

    public ChannelListener(ConsoleBridge Instance) {
        this.plugin = Instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMessage(PluginMessageEvent e) throws IOException {
        if((e.getReceiver() instanceof ProxiedPlayer && e.getSender() instanceof Server)) {
            Configuration config = plugin.configData;
            if (!e.getTag().equals("BungeeCord")) return;
            DataInputStream channel = new DataInputStream(new ByteArrayInputStream(e.getData()));
            String chan = channel.readUTF();
            String chan_pl = chan.substring(0, chan.indexOf(':'));
            String chan_method = chan.substring(chan.indexOf(':') + 1);

            if (chan_pl.equals("ConsoleBridge")) {
                String formatted_message = null;
                ProxiedPlayer player = (ProxiedPlayer) e.getReceiver();
                switch (chan_method) {
                    case "Chat":
                        String message = channel.readUTF();
                        if(config.getBoolean("Enabled-Chat")) formatted_message = config.getString("Format-Chat").replaceAll("%SERVER%", ((Server) e.getSender()).getInfo().getName()).replaceAll("%PLAYER_NAME%", player.getName()).replaceAll("%MESSAGE%", message);
                        break;

                    case "Command":
                        String command = channel.readUTF();
                        if(config.getBoolean("Enabled-Command")) formatted_message = config.getString("Format-Command").replaceAll("%SERVER%", ((Server) e.getSender()).getInfo().getName()).replaceAll("%PLAYER_NAME%", player.getName()).replaceAll("%COMMAND%", command);
                        break;

                    case "Death":
                        String death_message = channel.readUTF();
                        if(config.getBoolean("Enabled-Death")) formatted_message = config.getString("Format-Death").replaceAll("%SERVER%", ((Server) e.getSender()).getInfo().getName()).replaceAll("%PLAYER_NAME%", player.getName()).replaceAll("%DEATH_MESSAGE%", death_message);
                        break;
                }
                if(formatted_message != null) System.out.print("[CB]" + formatted_message);
            }
        }
    }
}
