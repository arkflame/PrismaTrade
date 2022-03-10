package dev._2lstudios.prismatrade.menus.items;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.hypermenus.HyperMenus;
import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.hypermenus.api.MenuItem;
import dev._2lstudios.prismatrade.menus.OrdersMenu;
import dev._2lstudios.prismatrade.trade.TradeService;
import dev._2lstudios.prismatrade.trade.TradeType;

public class CategoryItem extends MenuItem {
    private Menu menu;
    private TradeService tradeService;
    private Material material;
    private TradeType tradeType;

    public CategoryItem(Menu menu, TradeService tradeService, Material material, TradeType tradeType) {
        this.menu = menu;
        this.tradeService = tradeService;
        this.material = material;
        this.tradeType = tradeType;
        // TODO: Set displayname and lore
        setDisplayname(StringUtils.capitalize(material.name().replace("_", " ").toLowerCase()));
        setType(material);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        HumanEntity whoClicked = event.getWhoClicked();

        if (!(whoClicked instanceof Player)) {
            return;
        }
        
        Player player = (Player) whoClicked;

        HyperMenus.getAPI().openMenu(player, new OrdersMenu(menu, tradeService, 0, tradeType, material));
    }
}
