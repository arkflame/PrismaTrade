package dev._2lstudios.prismatrade.menus;

import org.bukkit.Material;

import dev._2lstudios.hypermenus.api.Menu;
import dev._2lstudios.hypermenus.api.NextItem;
import dev._2lstudios.hypermenus.api.PageMenu;
import dev._2lstudios.hypermenus.api.PreviousItem;
import dev._2lstudios.prismatrade.menus.items.BackItem;
import dev._2lstudios.prismatrade.menus.items.CategoryItem;
import dev._2lstudios.prismatrade.trade.TradeService;
import dev._2lstudios.prismatrade.trade.TradeType;
import dev._2lstudios.prismatrade.trade.entities.CategoryEntity;

public class CategoriesMenu extends PageMenu {
    public CategoriesMenu(Menu previousMenu, TradeService tradeService, int page, TradeType tradeType) {
        super(page);

        CategoryEntity[] categories = tradeService.getCategories();
        int maxPage = categories.length / Menu.CHECKERED_SLOTS.length;
        int index = page > 0 ? (Menu.CHECKERED_SLOTS.length * page - 1) : 0;

        for (int slot : Menu.CHECKERED_SLOTS) {
            if (categories.length > index) {
                String materialName = categories[index++].name;
                Material material = Material.getMaterial(materialName);

                if (material != null) {
                    setItem(slot, new CategoryItem(this, tradeService, material, tradeType));
                }
            }
        }

        if (page > 0)
            setItem(45, new PreviousItem(this).setType(Material.ARROW).setDisplayname("Previous"));

        setItem(49, new BackItem(previousMenu));

        if (page < maxPage)
            setItem(53, new NextItem(this).setType(Material.ARROW).setDisplayname("Next"));

        if (tradeType == TradeType.DEMAND) {
            setTitle("Categorias (Vender)");
        } else {
            setTitle("Categorias (Comprar)");
        }

        setSize(54);
    }
}
