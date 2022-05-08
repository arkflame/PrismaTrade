package dev._2lstudios.prismatrade.interfaces.items;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import dev._2lstudios.interfacemaker.interfaces.InterfaceItem;
import dev._2lstudios.prismatrade.PrismaTradeAPI;
import dev._2lstudios.prismatrade.entities.BuyOrder;

public class BuyEntryItem extends InterfaceItem {
    private Configuration config;
    private String buyOrderID;
    private PrismaTradeAPI prismaTrade;

    public BuyEntryItem(Configuration config, BuyOrder buyOrder, PrismaTradeAPI prismaTrade) {
        Material material = buyOrder.getMaterial();
        String materialName = material.name();
        String amount = String.valueOf(buyOrder.getAmount());
        String price = String.valueOf(buyOrder.getPrice());
        String owner = buyOrder.getOwnerName();

        this.config = config;
        this.buyOrderID = buyOrder.getId();
        this.prismaTrade = prismaTrade;

        setType(material);
        setName(config.getString("messages.buy-entry-name").replace("%category%", materialName)
                .replace("%amount%", amount).replace("%price%", price).replace("%owner%", owner));
        setLore(config.getString("messages.buy-entry-lore").replace("%category%", materialName)
                .replace("%amount%", amount).replace("%price%", price).replace("%owner%", owner));
    }

    @Override
    public void onClick(Player player, Inventory clickedInventory) {
        // TODO: Sell items to this little guy?
    }
}
