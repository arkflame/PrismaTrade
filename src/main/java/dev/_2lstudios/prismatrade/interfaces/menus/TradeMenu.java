package dev._2lstudios.prismatrade.interfaces.menus;

import org.bukkit.configuration.Configuration;

import dev._2lstudios.interfacemaker.interfaces.InterfaceMenu;
import dev._2lstudios.interfacemaker.interfaces.contexts.MenuBuildContext;
import dev._2lstudios.prismatrade.PrismaTradeAPI;
import dev._2lstudios.prismatrade.interfaces.items.BuyItem;
import dev._2lstudios.prismatrade.interfaces.items.SellItem;

public class TradeMenu extends InterfaceMenu {
    private Configuration config;
    private PrismaTradeAPI prismaTrade;

    public TradeMenu(Configuration config, PrismaTradeAPI prismaTrade) {
        this.config = config;
        this.prismaTrade = prismaTrade;
    }

    @Override
    public void onBuild(MenuBuildContext context) {
        setItem(10, new BuyItem(config, prismaTrade, this));
        setItem(13, new SellItem(config, prismaTrade, this));
    }
}
