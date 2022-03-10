package dev._2lstudios.prismatrade;

import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.prismatrade.commands.SellCommand;
import dev._2lstudios.prismatrade.commands.TradeCommand;
import dev._2lstudios.prismatrade.menus.PrismaTradeMenu;
import dev._2lstudios.prismatrade.trade.TradeController;
import dev._2lstudios.prismatrade.trade.TradeService;

public class PrismaTrade extends JavaPlugin {
    private static PrismaTrade instance;

    public static PrismaTrade getInstance () {
        return PrismaTrade.instance;
    }

    private TradeService tradeService;
    private TradeController tradeController;
    private PrismaTradeMenu prismaTradeMenu;

    @Override
    public void onEnable () {
        saveDefaultConfig();

        PrismaTrade.instance = this;
        
        tradeService = new TradeService(this);
        tradeController = new TradeController(tradeService);
        prismaTradeMenu = new PrismaTradeMenu(tradeService);

        SellCommand sellCommand = new SellCommand(tradeController);

        getCommand("sell").setExecutor(sellCommand);
        getCommand("trade").setExecutor(new TradeCommand(this, sellCommand));
    }

    public PrismaTradeMenu getMenu() {
        return prismaTradeMenu;
    }

    public TradeService getTradeService() {
        return tradeService;
    }

    public TradeController getTradeController() {
        return tradeController;
    }
}