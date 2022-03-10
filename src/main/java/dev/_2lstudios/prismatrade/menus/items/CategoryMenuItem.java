package dev._2lstudios.prismatrade.menus.items;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.hypermenus.HyperMenus;
import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.hypermenus.api.MenuItem;
import dev._2lstudios.prismatrade.menus.CategoriesMenu;
import dev._2lstudios.prismatrade.trade.TradeService;
import dev._2lstudios.prismatrade.trade.TradeType;

public class CategoryMenuItem extends MenuItem {
    private Menu menu;
    private TradeService tradeService;
    private TradeType tradeType;

    public CategoryMenuItem(Menu menu, TradeService tradeService, TradeType tradeType) {
        this.menu = menu;
        this.tradeService = tradeService;
        this.tradeType = tradeType;
        // TODO: Set displayname and lore
        if (tradeType == TradeType.DEMAND) {
            setDisplayname("Vender");
            setType(Material.DIAMOND_BLOCK);
        } else {
            setDisplayname("Comprar");
            setType(Material.EMERALD_BLOCK);
        }
        ;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        HumanEntity whoClicked = event.getWhoClicked();

        if (!(whoClicked instanceof Player)) {
            return;
        }

        Player player = (Player) whoClicked;

        HyperMenus.getAPI().openMenu(player, new CategoriesMenu(menu, tradeService, 0, tradeType));
    }
}
