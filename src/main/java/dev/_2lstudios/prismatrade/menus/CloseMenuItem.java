package dev._2lstudios.prismatrade.menus;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.hypermenus.api.MenuItem;

public class CloseMenuItem extends MenuItem {
    public CloseMenuItem() {
        setType(Material.BARRIER);
        setDisplayname("Cerrar");
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.getWhoClicked().closeInventory();
    }
}
