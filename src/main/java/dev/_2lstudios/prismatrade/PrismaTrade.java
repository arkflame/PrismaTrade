package dev._2lstudios.prismatrade;

import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.prismatrade.commands.TradeCommand;
import dev._2lstudios.prismatrade.menus.PrismaTradeMenu;
import dev._2lstudios.prismatrade.trade.TradeService;

public class PrismaTrade extends JavaPlugin {
    private static PrismaTrade instance;
    private TradeService tradeService;

    public static PrismaTrade getInstance () {
        return PrismaTrade.instance;
    }

    private PrismaTradeMenu prismaTradeMenu;

    @Override
    public void onEnable () {
        saveDefaultConfig();

        PrismaTrade.instance = this;
        
        tradeService = new TradeService(this);
        prismaTradeMenu = new PrismaTradeMenu(tradeService);

        getCommand("trade").setExecutor(new TradeCommand(this));
    }

    public PrismaTradeMenu getMenu() {
        return prismaTradeMenu;
    }

    public TradeService getTradeService() {
        return tradeService;
    }
}