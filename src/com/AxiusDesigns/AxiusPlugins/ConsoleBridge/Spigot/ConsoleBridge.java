package com.AxiusDesigns.AxiusPlugins.ConsoleBridge.Spigot;

import com.AxiusDesigns.AxiusPlugins.ConsoleBridge.Spigot.Events.ChatEvents;
import com.AxiusDesigns.AxiusPlugins.ConsoleBridge.Spigot.Utilities.ServerUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ConsoleBridge extends JavaPlugin {
    public String prefix = "[ConsoleBridge] ";

    @Override
    public void onEnable() {
        System.out.print(prefix + "Enabling plugin");

        //FILES
        System.out.print("- Checking files (1/2)");
        File data = new File(this.getDataFolder().getParentFile() + File.separator + "AxiusPlugins");
        if(!data.exists()) {
            System.out.print("- File not found, creating (1/2)");
            data.mkdir();
            System.out.print("- Checking files (2/2)");
        }
        else System.out.print("- File found (2/2)");

        File rph = new File(data + File.separator + "PlayerLimiter");
        if(!rph.exists()) {
            System.out.print("- File not found, creating (2/2)");
            rph.mkdir();
        }
        else System.out.print("- File found (1/2)");

        System.out.print("- Registering outgoing channel");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        //EVENTS
        getServer().getPluginManager().registerEvents(new ChatEvents(this), this);

        System.out.print(prefix + "Plugin enabled.");

    }
}
