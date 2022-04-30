package dev._2lstudios.prismatrade.entities;

import java.io.IOException;

import com.dotphin.milkshakeorm.entity.Prop;

import org.bukkit.inventory.ItemStack;

import dev._2lstudios.prismatrade.utils.BukkitSerialization;

public class SellOrder extends BuyOrder {
    @Prop
    public String serializedItem;

    public ItemStack getItemStack() {
        if (serializedItem != null) {
            try {
                ItemStack[] items = BukkitSerialization.itemStackArrayFromBase64(serializedItem);

                if (items.length > 0) {
                    ItemStack item = items[0];

                    item.setAmount(1);

                    return item;
                }
            } catch (IOException ex) {
                // Ignored
            }
        }

        return null;
    }

    public SellOrder setItemStack(ItemStack item) {
        if (item != null) {
            item.setAmount(1);

            this.serializedItem = BukkitSerialization.itemStackArrayToBase64(item);
        } else {
            this.serializedItem = null;
        }

        return this;
    }
}
