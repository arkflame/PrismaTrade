package dev._2lstudios.prismatrade.menus.items;

import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.hypermenus.api.MenuItem;

public class NewOrderMenuItem extends MenuItem {
    public NewOrderMenuItem() {
        // TODO: Set displayname and lore
        setDisplayname("New Order");
    }

    @Override
    public void onClick(final InventoryClickEvent event) {
        // TODO: Ask for player input to make order (Material, amount, price)
    }
}
