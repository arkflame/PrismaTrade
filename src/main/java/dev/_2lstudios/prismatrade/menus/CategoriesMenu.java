package dev._2lstudios.prismatrade.menus;

import org.bukkit.Material;

import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.hypermenus.api.NextItem;
import dev._2lstudios.hypermenus.api.PageMenu;
import dev._2lstudios.hypermenus.api.PreviousItem;
import dev._2lstudios.prismatrade.menus.items.CategoryItem;
import dev._2lstudios.prismatrade.trade.CategoryEntity;
import dev._2lstudios.prismatrade.trade.TradeService;
import dev._2lstudios.prismatrade.trade.TradeType;

public class CategoriesMenu extends PageMenu {
    public CategoriesMenu(TradeService tradeService, int page, TradeType tradeType) {
        super(page);

        CategoryEntity[] categories = tradeService.getCategories();
        int index = page > 0 ? (Menu.CHECKERED_SLOTS.length * page - 1) : 0;

        for (int slot : Menu.CHECKERED_SLOTS) {
            if (categories.length >= index) {
                break;
            }

            String materialName = categories[index++].name;
            Material material = Material.getMaterial(materialName);

            if (material != null) {
                setItem(slot, new CategoryItem(tradeService, material));
            }
        }

        setItem(45, new PreviousItem(this));
        setItem(53, new NextItem(this));
        setTitle("Categories");
        setSize(54);
    }
}
