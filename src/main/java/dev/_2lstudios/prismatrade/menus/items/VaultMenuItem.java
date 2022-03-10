package dev._2lstudios.prismatrade.menus.items;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.hypermenus.api.MenuItem;

public class VaultMenuItem extends MenuItem {
    public VaultMenuItem() {
        // TODO: Set displayname and lore
        setDisplayname("Vault");
        setType(Material.CHEST);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        // TODO: Open inventory menu
    }
}
