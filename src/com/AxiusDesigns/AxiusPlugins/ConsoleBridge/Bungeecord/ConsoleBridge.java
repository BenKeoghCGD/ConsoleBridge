package com.AxiusDesigns.AxiusPlugins.ConsoleBridge.Bungeecord;

import com.AxiusDesigns.AxiusPlugins.ConsoleBridge.Bungeecord.Events.ChannelListener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConsoleBridge extends Plugin {
    public String prefix = "[ConsoleBridge] ";

    public Configuration configData;

    @Override
    public void onEnable() {
        System.out.print(prefix + "Enabling plugin");

        //FILES


        System.out.print("- Checking files (1/2)");
        File data = new File(this.getDataFolder().getParentFile() + File.separator + "AxiusPlugins");
        if(!data.exists()) {
            System.out.print("- File not found, creating (1/2)");
            data.mkdir();
        }
        else System.out.print("- File found (1/2)");

        System.out.print("- Checking files (2/2)");
        File a = new File(this.getDataFolder().getParentFile() + File.separator + "AxiusPlugins" + File.separator + "ConsoleBridge");
        if(!a.exists()) {
            System.out.print("- File not found, creating (2/2)");
            a.mkdir();
        }
        else System.out.print("- File found (2/2)");

        //CONF/MESSAGES
        System.out.print("- Loading config.yml");
        File file = new File(a, "config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            configData = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(a, "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //COMMANDS

        //EVENTS
        getProxy().getPluginManager().registerListener(this, new ChannelListener(this));

        System.out.print(prefix + "Plugin enabled.");

    }
}
