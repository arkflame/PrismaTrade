package dev._2lstudios.prismatrade.menus;

import org.bukkit.Material;

import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.hypermenus.api.NextItem;
import dev._2lstudios.hypermenus.api.PageMenu;
import dev._2lstudios.hypermenus.api.PreviousItem;
import dev._2lstudios.prismatrade.menus.items.CategoryItem;
import dev._2lstudios.prismatrade.trade.TradeService;
import dev._2lstudios.prismatrade.trade.TradeType;

public class CategoryMenu extends PageMenu {
    public CategoryMenu(TradeService tradeService, int page, TradeType tradeType) {
        super(page);

        Material[] materials = Material.values();
        int index = Menu.CHECKERED_SLOTS.length * (page - 1);

        index = index >= 1 ? index : 1;

        for (int slot : Menu.CHECKERED_SLOTS) {
            Material material = materials[++index];

            setItem(slot, new CategoryItem(tradeService, material));
        }

        setItem(45, new PreviousItem(this));
        setItem(53, new NextItem(this));
        setTitle("Categories");
        setSize(54);
    }
}
