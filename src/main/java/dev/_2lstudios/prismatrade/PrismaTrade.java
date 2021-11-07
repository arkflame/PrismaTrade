package dev._2lstudios.prismatrade;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.prismatrade.commands.TradeCommand;
import dev._2lstudios.prismatrade.listeners.PlayerJoinListener;

public class PrismaTrade extends JavaPlugin {
    private static PrismaTrade instance;

    public static PrismaTrade getInstance () {
        return PrismaTrade.instance;
    }

    @Override
    public void onEnable () {
        final PluginManager pluginManager = getServer().getPluginManager();

        saveDefaultConfig();

        PrismaTrade.instance = this;

        getCommand("trade").setExecutor(new TradeCommand());
        
        pluginManager.registerEvents(new PlayerJoinListener(), this);
    }
}