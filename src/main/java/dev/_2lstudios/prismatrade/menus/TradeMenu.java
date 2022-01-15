package dev._2lstudios.prismatrade.menus;

import org.bukkit.Material;

import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.hypermenus.api.NextItem;
import dev._2lstudios.hypermenus.api.PageMenu;
import dev._2lstudios.hypermenus.api.PreviousItem;
import dev._2lstudios.prismatrade.menus.items.TradeItem;
import dev._2lstudios.prismatrade.trade.TradeEntity;
import dev._2lstudios.prismatrade.trade.TradeService;
import dev._2lstudios.prismatrade.trade.TradeType;

public class TradeMenu extends PageMenu {
    public TradeMenu(TradeService tradeService, int page, TradeType tradeType, Material material) {
        super(page);
        TradeEntity[] orders = tradeType == TradeType.BUY ? tradeService.getOffers(28, 0) : tradeService.getDemands(28, 0);
        int i = 0;

        for (int slot : Menu.CHECKERED_SLOTS) {
            if (orders.length <= i) {
                break;
            }

            TradeEntity tradeEntity = orders[i++];
            setItem(slot, new TradeItem(tradeEntity, tradeType));
        }

        setItem(45, new PreviousItem(this));
        setItem(53, new NextItem(this));
        setTitle("Trading");
        setSize(54);
    }
}
