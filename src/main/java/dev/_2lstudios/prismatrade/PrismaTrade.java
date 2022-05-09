package dev._2lstudios.prismatrade;

import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.prismatrade.commands.admin.MainCommand;
import dev._2lstudios.prismatrade.commands.player.BuyCommand;
import dev._2lstudios.prismatrade.commands.player.SellCommand;
import dev._2lstudios.prismatrade.commands.player.TradeCommand;
import dev._2lstudios.prismatrade.config.ConfigManager;
import dev._2lstudios.prismatrade.config.Configuration;

public class PrismaTrade extends JavaPlugin {
    private static PrismaTradeAPI api;

    public static PrismaTradeAPI getAPI() {
        return PrismaTrade.api;
    }

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        // Load initial configuration files.
        this.configManager = new ConfigManager(this);
        this.reload();

        PrismaTrade.api = new PrismaTradeAPI(getServer(), this.getMainConfig());

        // Admin & Console commands (Require permission prismatrade.admin)
        new MainCommand().register(this);

        // Player commands (No permissions required)
        new BuyCommand().register(this);
        new SellCommand().register(this);
        new TradeCommand().register(this);
    }

    public void reload(){
        try {
            this.getMainConfig().load();
            this.getMessages().load();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Configuration getMainConfig() {
        return this.configManager.getConfig("config.yml");
    }

    public Configuration getMessages() {
        return this.configManager.getConfig("messages.yml");
    }
}