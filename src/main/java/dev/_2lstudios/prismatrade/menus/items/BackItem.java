package dev._2lstudios.prismatrade.menus.items;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.hypermenus.HyperMenus;
import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.hypermenus.api.MenuItem;

public class BackItem extends MenuItem {
    private Menu menu;

    public BackItem(Menu menu) {
        this.menu = menu;
        // TODO: Set displayname and lore
        setDisplayname("Atras");
        setType(Material.ARROW);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        HumanEntity whoClicked = event.getWhoClicked();

        if (whoClicked instanceof Player) {
            HyperMenus.getAPI().openMenu((Player) whoClicked, menu);
        }
    }
}
