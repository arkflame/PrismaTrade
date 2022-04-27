package dev._2lstudios.prismatrade.entities;

import java.io.IOException;
import java.util.UUID;

import com.dotphin.milkshakeorm.entity.Prop;

import org.bukkit.inventory.ItemStack;

import dev._2lstudios.prismatrade.utils.BukkitSerialization;

public class SellOrder extends BuyOrder {
    @Prop
    public String serializedItem;

    @Prop
    public String ownerName;

    @Prop
    public String ownerUUID;

    public String getOwnerName() {
        return ownerName;
    }

    public SellOrder setOwnerName(String ownerName) {
        this.ownerName = ownerName;

        return this;
    }

    public UUID getOwnerUUID() {
        return UUID.fromString(ownerUUID);
    }

    public SellOrder setOwnerUUID(UUID uuid) {
        this.ownerUUID = uuid.toString();

        return this;
    }

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
