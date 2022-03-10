package dev._2lstudios.prismatrade.menus;

import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.prismatrade.trade.TradeService;
import dev._2lstudios.prismatrade.trade.TradeType;
import dev._2lstudios.prismatrade.menus.items.VaultMenuItem;
import dev._2lstudios.prismatrade.menus.items.CategoryMenuItem;

public class PrismaTradeMenu extends Menu {
    public PrismaTradeMenu(TradeService tradeService) {
        setSize(27);
        setItem(10, new CategoryMenuItem(this, tradeService, TradeType.OFFER));
        setItem(13, new CategoryMenuItem(this, tradeService, TradeType.DEMAND));
        setItem(16, new VaultMenuItem());
        setItem(22, new CloseMenuItem());
        setTitle("PrismaTrade");
    }
}
