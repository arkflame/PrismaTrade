package dev._2lstudios.prismatrade.interfaces.items;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import dev._2lstudios.interfacemaker.interfaces.InterfaceItem;
import dev._2lstudios.interfacemaker.interfaces.InterfaceMenu;
import dev._2lstudios.prismatrade.interfaces.menus.PageMenu;

public class PageItem extends InterfaceItem {
    private InterfaceMenu menu;

    public PageItem(Configuration config, PageMenu menu, PageType type) {
        this.menu = menu;
        setType(Material.ARROW);
        String page = String.valueOf(menu.getPage());

        switch (type) {
            case NEXT: {
                setName(config.getString("menu.next-page-item").replace("%page%", page));
                break;
            }
            case PREVIOUS: {
                setName(config.getString("menu.prev-page-item").replace("%page%", page));
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public void onClick(Player player, Inventory clickedInventory) {
        menu.build(player);
    }
}
