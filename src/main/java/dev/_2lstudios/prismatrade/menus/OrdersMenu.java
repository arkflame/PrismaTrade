package dev._2lstudios.prismatrade.menus;

import org.bukkit.Material;

import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.hypermenus.api.NextItem;
import dev._2lstudios.hypermenus.api.PageMenu;
import dev._2lstudios.hypermenus.api.PreviousItem;
import dev._2lstudios.prismatrade.menus.items.BackItem;
import dev._2lstudios.prismatrade.menus.items.TradeItem;
import dev._2lstudios.prismatrade.trade.TradeService;
import dev._2lstudios.prismatrade.trade.TradeType;
import dev._2lstudios.prismatrade.trade.entities.TradeEntity;

public class OrdersMenu extends PageMenu {
    public OrdersMenu(Menu previousMenu, TradeService tradeService, int page, TradeType tradeType, Material material) {
        super(page);
        TradeEntity[] orders = tradeType == TradeType.DEMAND ? tradeService.getDemands(material.name(), 28, 0)
                : tradeService.getOffers(material.name(), 28, 0);
        int maxPage = orders.length / Menu.CHECKERED_SLOTS.length;
        int i = 0;

        for (int slot : Menu.CHECKERED_SLOTS) {
            if (orders.length <= i) {
                break;
            }

            TradeEntity tradeEntity = orders[i++];
            setItem(slot, new TradeItem(tradeEntity, tradeType));
        }

        if (page > 1)
            setItem(45, new PreviousItem(this).setType(Material.ARROW).setDisplayname("Previous"));

        setItem(49, new BackItem(previousMenu));

        if (page < maxPage)
            setItem(53, new NextItem(this).setType(Material.ARROW).setDisplayname("Next"));

        if (tradeType == TradeType.DEMAND) {
            setTitle("Demandas (Vender)");
        } else {
            setTitle("Ofertas (Comprar)");
        }

        setSize(54);
    }
}
