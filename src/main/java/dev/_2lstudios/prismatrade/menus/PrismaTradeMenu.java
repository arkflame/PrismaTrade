package dev._2lstudios.prismatrade.menus;

import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.prismatrade.menus.items.BuyMenuItem;
import dev._2lstudios.prismatrade.menus.items.InventoryMenuItem;
import dev._2lstudios.prismatrade.menus.items.NewOrderMenuItem;
import dev._2lstudios.prismatrade.menus.items.SellMenuItem;

public class PrismaTradeMenu extends Menu {
    public PrismaTradeMenu() {
        setSize(27);
        setItem(10, new BuyMenuItem());
        setItem(12, new SellMenuItem());
        setItem(14, new NewOrderMenuItem());
        setItem(16, new InventoryMenuItem());
    }
}
