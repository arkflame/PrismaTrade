package dev._2lstudios.prismatrade.menus.items;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.hypermenus.api.MenuItem;
import dev._2lstudios.prismatrade.trade.TradeEntity;
import dev._2lstudios.prismatrade.trade.TradeType;

public class TradeItem extends MenuItem {
    public TradeItem(TradeEntity tradeEntity, TradeType tradeType) {
        // TODO: Set displayname and lore
        Material material = Material.getMaterial(tradeEntity.material);
        setDisplayname(tradeEntity.material + " (" + tradeEntity.id + ")");
        setType(material == null ? Material.STONE : material);
    }

    @Override
    public void onClick(final InventoryClickEvent event) {
        // TODO: Open buy menu
    }
}
