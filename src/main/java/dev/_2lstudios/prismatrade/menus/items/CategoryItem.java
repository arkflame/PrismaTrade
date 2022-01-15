package dev._2lstudios.prismatrade.menus.items;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.hypermenus.HyperMenus;
import dev._2lstudios.hypermenus.api.MenuItem;
import dev._2lstudios.prismatrade.menus.TradeMenu;
import dev._2lstudios.prismatrade.trade.TradeService;
import dev._2lstudios.prismatrade.trade.TradeType;

public class CategoryItem extends MenuItem {
    private TradeService tradeService;
    private Material material;

    public CategoryItem(TradeService tradeService, Material material) {
        this.tradeService = tradeService;
        this.material = material;
        // TODO: Set displayname and lore
        setDisplayname("Category: " + material);
        setType(material);
    }

    @Override
    public void onClick(final InventoryClickEvent event) {
        HumanEntity whoClicked = event.getWhoClicked();

        if (!(whoClicked instanceof Player)) {
            return;
        }
        
        Player player = (Player) whoClicked;

        HyperMenus.getAPI().openMenu(player, new TradeMenu(tradeService, 0, TradeType.BUY, material));
    }
}
