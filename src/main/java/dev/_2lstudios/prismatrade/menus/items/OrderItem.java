package dev._2lstudios.prismatrade.menus.items;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.hypermenus.api.MenuItem;

public class OrderItem extends MenuItem {
    public OrderItem(final int amount, final Material type, final int price) {
        // TODO: Set displayname and lore
        setDisplayname("x" + amount + " " + type + " $" + price);
    }

    @Override
    public void onClick(final InventoryClickEvent event) {
        // TODO: Open buy menu
    }
}