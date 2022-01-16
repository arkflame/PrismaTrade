package dev._2lstudios.prismatrade.menus.items;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.hypermenus.HyperMenus;
import dev._2lstudios.hypermenus.api.MenuItem;
import dev._2lstudios.prismatrade.menus.CategoriesMenu;
import dev._2lstudios.prismatrade.trade.TradeService;
import dev._2lstudios.prismatrade.trade.TradeType;

public class CategoryMenuItem extends MenuItem {
    private TradeService tradeService;
    private TradeType tradeType;

    public CategoryMenuItem(TradeService tradeService, TradeType tradeType) {
        this.tradeService = tradeService;
        this.tradeType = tradeType;
        // TODO: Set displayname and lore
        setDisplayname(tradeType.name());
    }

    @Override
    public void onClick(final InventoryClickEvent event) {
        HumanEntity whoClicked = event.getWhoClicked();

        if (!(whoClicked instanceof Player)) {
            return;
        }

        Player player = (Player) whoClicked;

        HyperMenus.getAPI().openMenu(player, new CategoriesMenu(tradeService, 0, tradeType));
    }
}
