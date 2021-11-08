package dev._2lstudios.prismatrade;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.prismatrade.commands.TradeCommand;
import dev._2lstudios.prismatrade.listeners.PlayerJoinListener;
import dev._2lstudios.prismatrade.menus.PrismaTradeMenu;

public class PrismaTrade extends JavaPlugin {
    private static PrismaTrade instance;

    public static PrismaTrade getInstance () {
        return PrismaTrade.instance;
    }

    private PrismaTradeMenu prismaTradeMenu;

    @Override
    public void onEnable () {
        final PluginManager pluginManager = getServer().getPluginManager();

        saveDefaultConfig();

        PrismaTrade.instance = this;

        prismaTradeMenu = new PrismaTradeMenu();

        getCommand("trade").setExecutor(new TradeCommand(this));
        
        pluginManager.registerEvents(new PlayerJoinListener(), this);
    }

    public PrismaTradeMenu getMenu() {
        return prismaTradeMenu;
    }
}