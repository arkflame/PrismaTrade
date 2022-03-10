package dev._2lstudios.prismatrade.menus.items;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.hypermenus.api.MenuItem;
import dev._2lstudios.prismatrade.trade.TradeType;
import dev._2lstudios.prismatrade.trade.entities.TradeEntity;

public class TradeItem extends MenuItem {
    public TradeItem(TradeEntity tradeEntity, TradeType tradeType) {
        // TODO: Set displayname and lore
        Material material = Material.getMaterial(tradeEntity.material);
        setDisplayname(StringUtils.capitalize(tradeEntity.material.replace("_", " ").toLowerCase()) + " (" + tradeEntity.id + ")");
        setType(material == null ? Material.STONE : material);
        setLore(Arrays.asList("Cantidad: " + tradeEntity.amount, "Precio: $" + tradeEntity.price, "Vendedor: " + tradeEntity.ownerId));
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        // TODO: Open buy menu
    }
}
