package dev._2lstudios.prismatrade.interfaces.items;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import dev._2lstudios.interfacemaker.interfaces.InterfaceItem;
import dev._2lstudios.interfacemaker.interfaces.InterfaceMenu;
import dev._2lstudios.prismatrade.PrismaTradeAPI;
import dev._2lstudios.prismatrade.interfaces.menus.CategoryMenu;
import dev._2lstudios.prismatrade.interfaces.menus.OrderType;

public class BuyItem extends InterfaceItem {
    private Configuration config;
    private PrismaTradeAPI prismaTrade;
    private InterfaceMenu menu;

    public BuyItem(Configuration config, PrismaTradeAPI prismaTrade,
            InterfaceMenu menu) {
        this.config = config;
        this.prismaTrade = prismaTrade;
        this.menu = menu;

        setName(config.getString("messages.buy-item-name"));
        setLore(config.getString("messages.buy-item-lore"));
        setType(Material.EMERALD);
    }

    @Override
    public void onClick(Player player, Inventory clickedInventory) {
        new CategoryMenu(1, config, prismaTrade, OrderType.BUY, menu).build(player);
    }
}
