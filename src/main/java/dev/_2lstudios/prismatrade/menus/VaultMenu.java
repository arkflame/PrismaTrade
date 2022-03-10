package dev._2lstudios.prismatrade.menus;

import org.bukkit.Material;

import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.hypermenus.api.MenuItem;
import dev._2lstudios.hypermenus.api.NextItem;
import dev._2lstudios.hypermenus.api.PageMenu;
import dev._2lstudios.hypermenus.api.PreviousItem;
import dev._2lstudios.prismatrade.menus.items.BackItem;
import dev._2lstudios.prismatrade.menus.items.TakeAllItem;

public class VaultMenu extends PageMenu {
    public VaultMenu(Menu previousMenu, int page) {
        super(page);

        for (int slot : Menu.CHECKERED_SLOTS) {
            setItem(slot, new MenuItem().setType(Material.DIAMOND).setAmount(64));
        }

        setItem(45, new PreviousItem(this));
        setItem(48, new TakeAllItem());
        setItem(49, new BackItem(previousMenu));
        setItem(53, new NextItem(this));
        setTitle("Vault");
        setSize(54);
    }
}
