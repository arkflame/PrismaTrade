package dev._2lstudios.prismatrade.interfaces.items;

import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import dev._2lstudios.interfacemaker.interfaces.InterfaceItem;
import dev._2lstudios.interfacemaker.interfaces.InterfaceMenu;
import dev._2lstudios.prismatrade.PrismaTradeAPI;
import dev._2lstudios.prismatrade.interfaces.menus.CategoryMenu;

public class CategoryItem extends InterfaceItem {
    private Configuration config;
    private PrismaTradeAPI prismaTrade;
    private CategoryItemType categoryItemType;
    private InterfaceMenu menu;

    public CategoryItem(Configuration config, PrismaTradeAPI prismaTrade, CategoryItemType categoryItemType,
            InterfaceMenu menu) {
        this.config = config;
        this.prismaTrade = prismaTrade;
        this.categoryItemType = categoryItemType;
        this.menu = menu;

        if (categoryItemType == CategoryItemType.BUY) {
            setName(config.getString("messages.buy-item-name"));
            setLore(config.getString("messages.buy-item-lore"));
        } else if (categoryItemType == CategoryItemType.SELL) {
            setName(config.getString("messages.sell-item-name"));
            setLore(config.getString("messages.sell-item-lore"));
        }
    }

    @Override
    public void onClick(Player player, Inventory clickedInventory) {
        new CategoryMenu(1, config, prismaTrade, categoryItemType, menu).build(player);
    }
}
