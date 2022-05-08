package dev._2lstudios.prismatrade.interfaces.menus;

import org.bukkit.configuration.Configuration;

import dev._2lstudios.interfacemaker.interfaces.InterfaceItem;
import dev._2lstudios.interfacemaker.interfaces.InterfaceMenu;
import dev._2lstudios.interfacemaker.interfaces.contexts.MenuBuildContext;
import dev._2lstudios.prismatrade.PrismaTradeAPI;
import dev._2lstudios.prismatrade.entities.Category;
import dev._2lstudios.prismatrade.interfaces.items.BackItem;
import dev._2lstudios.prismatrade.interfaces.items.CategoryEntryItem;
import dev._2lstudios.prismatrade.interfaces.items.PageItem;
import dev._2lstudios.prismatrade.interfaces.items.PageType;
import dev._2lstudios.prismatrade.utils.Placeholder;

public class CategoryMenu extends PageMenu {
    private static int MAX_ENTRY_COUNT = 21;

    private Configuration config;
    private PrismaTradeAPI prismaTrade;
    private OrderType orderType;
    private InterfaceMenu lastMenu;
    private int skipAmount;

    public CategoryMenu(int page, Configuration config, PrismaTradeAPI prismaTrade, OrderType orderType,
            InterfaceMenu lastMenu) {
        super(page);
        this.config = config;
        this.prismaTrade = prismaTrade;
        this.orderType = orderType;
        this.lastMenu = lastMenu;
        this.skipAmount = (page - 1) * MAX_ENTRY_COUNT;
        setRows(6);
    }

    @Override
    public void onBuild(MenuBuildContext context) {
        Category[] dataFound = prismaTrade.getCategories(skipAmount,
                MAX_ENTRY_COUNT + 1);
        InterfaceItem[] heads = new InterfaceItem[MAX_ENTRY_COUNT];

        context.setTitle(Placeholder.color(config.getString("messages.category-menu-title")
                .replace("%page%", String.valueOf(getPage())).replace("%type%", orderType.name())));

        for (int i = 0; i < MAX_ENTRY_COUNT && i < dataFound.length; i++) {
            Category data = dataFound[i];
            InterfaceItem item = new CategoryEntryItem(config, data, prismaTrade, orderType, this);

            heads[i] = item;
        }

        context.fill(1, heads);

        int page = getPage();

        if (page > 1) {
            context.setItem(45, new PageItem(config, new CategoryMenu(page - 1, config, prismaTrade, orderType, lastMenu),
                    PageType.PREVIOUS));
        } else {
            context.setItem(45, new BackItem(config, lastMenu));
        }

        if (dataFound.length > MAX_ENTRY_COUNT) {
            context.setItem(53, new PageItem(config, new CategoryMenu(page + 1, config, prismaTrade, orderType, lastMenu),
                    PageType.NEXT));
        }
    }
}
