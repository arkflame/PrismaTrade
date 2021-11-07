package dev._2lstudios.prismatrade.menus.items;

import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.hypermenus.api.MenuItem;

public class InventoryMenuItem extends MenuItem {
    public InventoryMenuItem() {
        // TODO: Set displayname and lore
        setDisplayname("Inventory");
    }

    @Override
    public void onClick(final InventoryClickEvent event) {
        // TODO: Open inventory menu
    }
}
