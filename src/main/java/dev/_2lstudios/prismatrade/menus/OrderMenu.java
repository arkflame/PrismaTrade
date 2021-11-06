package dev._2lstudios.prismatrade.menus;

import org.bukkit.Material;

import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.prismatrade.menus.items.AcceptOrderItem;
import dev._2lstudios.prismatrade.menus.items.CancelOrderItem;
import dev._2lstudios.prismatrade.menus.items.OrderItem;

public class OrderMenu extends Menu {
    public OrderMenu() {
        setSize(27);
        setItem(10, new AcceptOrderItem());
        setItem(13, new OrderItem(64, Material.OAK_WOOD, 599));
        setItem(16, new CancelOrderItem());
    }
}
