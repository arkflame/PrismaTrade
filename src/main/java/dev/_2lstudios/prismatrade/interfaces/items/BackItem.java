package dev._2lstudios.prismatrade.interfaces.items;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import dev._2lstudios.interfacemaker.interfaces.InterfaceItem;
import dev._2lstudios.interfacemaker.interfaces.InterfaceMenu;

public class BackItem extends InterfaceItem {
    private InterfaceMenu menu;

    public BackItem(Configuration config, InterfaceMenu menu) {
        this.menu = menu;
        setType(Material.ARROW);
        setName(config.getString("messages.back-name"));
    }

    @Override
    public void onClick(Player player, Inventory clickedInventory) {
        menu.build(player);
    }
}
