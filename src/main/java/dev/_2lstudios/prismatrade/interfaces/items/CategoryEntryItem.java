package dev._2lstudios.prismatrade.interfaces.items;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import dev._2lstudios.interfacemaker.interfaces.InterfaceItem;
import dev._2lstudios.interfacemaker.interfaces.InterfaceMenu;
import dev._2lstudios.prismatrade.PrismaTradeAPI;
import dev._2lstudios.prismatrade.entities.Category;
import dev._2lstudios.prismatrade.interfaces.menus.BuyMenu;
import dev._2lstudios.prismatrade.interfaces.menus.OrderType;
import dev._2lstudios.prismatrade.interfaces.menus.SellMenu;

public class CategoryEntryItem extends InterfaceItem {
    private Configuration config;
    private PrismaTradeAPI prismaTrade;
    private OrderType orderType;
    private Material material;
    private InterfaceMenu lastMenu;

    public CategoryEntryItem(Configuration config, Category category, PrismaTradeAPI prismaTrade, OrderType orderType,
            InterfaceMenu lastMenu) {
        this.config = config;
        this.prismaTrade = prismaTrade;
        this.orderType = orderType;
        this.material = Material.getMaterial(category.getName());
        this.lastMenu = lastMenu;
        setType(material);
        setName(config.getString("messages.category-entry-name").replace("%category%", category.getName()));
        setLore(config.getString("messages.category-entry-lore").replace("%category%", category.getName()));
    }

    @Override
    public void onClick(Player player, Inventory clickedInventory) {
        switch (orderType) {
            case BUY:
                new BuyMenu(1, config, prismaTrade, material, lastMenu).build(player);
            case SELL:
                new SellMenu(1, config, prismaTrade, material, lastMenu).build(player);
            default:
                break;
        }
    }
}
