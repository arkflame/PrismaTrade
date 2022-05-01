package dev._2lstudios.prismatrade.interfaces.items;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import dev._2lstudios.interfacemaker.interfaces.InterfaceItem;
import dev._2lstudios.interfacemaker.interfaces.InterfaceMenu;
import dev._2lstudios.prismatrade.PrismaTradeAPI;
import dev._2lstudios.prismatrade.entities.SellOrder;

public class SellEntryItem extends InterfaceItem {
    private Configuration config;
    private SellOrder sellOrder;
    private PrismaTradeAPI prismaTrade;
    private InterfaceMenu lastMenu;

    public SellEntryItem(Configuration config, SellOrder sellOrder, PrismaTradeAPI prismaTrade,
            InterfaceMenu lastMenu) {
        Material material = sellOrder.getMaterial();
        String materialName = material.name();
        String amount = String.valueOf(sellOrder.getAmount());
        String price = String.valueOf(sellOrder.getPrice());
        String owner = sellOrder.getOwnerName();

        this.config = config;
        this.sellOrder = sellOrder;
        this.prismaTrade = prismaTrade;
        this.lastMenu = lastMenu;
        setType(material);
        setName(config.getString("messages.sell-entry-name").replace("%category%", materialName)
                .replace("%amount%", amount).replace("%price%", price).replace("%owner%", owner));
        setLore(config.getString("messages.sell-entry-lore").replace("%category%", materialName)
                .replace("%amount%", amount).replace("%price%", price).replace("%owner%", owner));
    }

    @Override
    public void onClick(Player player, Inventory clickedInventory) {

    }
}
