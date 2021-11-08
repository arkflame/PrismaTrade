package dev._2lstudios.prismatrade.menus;

import org.bukkit.Material;

import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.hypermenus.api.MenuItem;
import dev._2lstudios.hypermenus.api.NextItem;
import dev._2lstudios.hypermenus.api.PageMenu;
import dev._2lstudios.hypermenus.api.PreviousItem;
import dev._2lstudios.prismatrade.menus.items.NewOrderMenuItem;

public class MarketMenu extends PageMenu {
    public MarketMenu(final int page) {
        super(page);

        for (final int slot : Menu.CHECKERED_SLOTS) {
            setItem(slot, new MenuItem().setType(Material.OAK_WOOD));
        }

        setItem(45, new PreviousItem(this));
        setItem(49, new NewOrderMenuItem());
        setItem(53, new NextItem(this));
    }
}
