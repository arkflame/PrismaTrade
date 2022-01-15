package dev._2lstudios.prismatrade;

import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.prismatrade.commands.TradeCommand;
import dev._2lstudios.prismatrade.menus.PrismaTradeMenu;

public class PrismaTrade extends JavaPlugin {
    private static PrismaTrade instance;

    public static PrismaTrade getInstance () {
        return PrismaTrade.instance;
    }

    private PrismaTradeMenu prismaTradeMenu;

    @Override
    public void onEnable () {
        saveDefaultConfig();

        PrismaTrade.instance = this;

        prismaTradeMenu = new PrismaTradeMenu();

        getCommand("trade").setExecutor(new TradeCommand(this));
    }

    public PrismaTradeMenu getMenu() {
        return prismaTradeMenu;
    }
}