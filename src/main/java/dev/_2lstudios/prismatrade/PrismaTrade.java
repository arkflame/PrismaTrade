package dev._2lstudios.prismatrade;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.prismatrade.commands.BuyCommand;
import dev._2lstudios.prismatrade.commands.SellCommand;
import dev._2lstudios.prismatrade.commands.TradeCommand;

public class PrismaTrade extends JavaPlugin {
    private static PrismaTradeAPI api;

    public static PrismaTradeAPI getAPI() {
        return PrismaTrade.api;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Configuration config = getConfig();
        PrismaTrade.api = new PrismaTradeAPI(getServer(), config);
        SellCommand sellCommand = new SellCommand(api);
        BuyCommand buyCommand = new BuyCommand(api);
        TradeCommand tradeCommand = new TradeCommand(api);

        getCommand("sell").setExecutor(sellCommand);
        getCommand("buy").setExecutor(buyCommand);
        getCommand("trade").setExecutor(tradeCommand);
    }
}