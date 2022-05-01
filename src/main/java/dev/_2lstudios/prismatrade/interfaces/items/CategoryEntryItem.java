package dev._2lstudios.prismatrade.interfaces.items;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;

import dev._2lstudios.interfacemaker.interfaces.InterfaceItem;
import dev._2lstudios.prismatrade.entities.Category;

public class CategoryEntryItem extends InterfaceItem {
    public CategoryEntryItem(Configuration config, Category category) {
        setType(Material.getMaterial(category.getName()));
        setName(config.getString("messages.category-entry-name").replace("%category%", category.getName()));
        setLore(config.getString("messages.category-entry-lore").replace("%category%", category.getName()));
    }
}
