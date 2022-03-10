package dev._2lstudios.prismatrade.menus;

import org.bukkit.entity.Player;

import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.prismatrade.menus.items.BackItem;

public class MyOrdersMenu extends Menu {
    public MyOrdersMenu(Player player, Menu previousMenu) {
        // TODO: Show orders by player (OrderItem)
        // TODO: Add button to go next/previous page
        setItem(49, new BackItem(previousMenu));
        // TODO: Add button to take all possible items
    }
}
