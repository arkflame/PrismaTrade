package dev._2lstudios.prismatrade.commands.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import dev._2lstudios.prismatrade.commands.BaseCommand;

public class SellCommand extends BaseCommand {
    public SellCommand() {
        super("sell", 1);
    }

    public int getInteger(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {}
        return 0;
    }

    public ItemStack getItemInHand(Player player) {
        PlayerInventory inventory = player.getInventory();
        int slot = inventory.getHeldItemSlot();
        return inventory.getItem(slot);
    }

    public void removeItemInHand(Player player) {
        PlayerInventory inventory = player.getInventory();
        int slot = inventory.getHeldItemSlot();
        inventory.setItem(slot, null);
    }
    
    @Override
    public void onExecutedByPlayer(Player player, String label, String[] args) {
        ItemStack hand = this.getItemInHand(player);
        int sellPrice = this.getInteger(args[0]);

        if (sellPrice < 1) {
            player.sendMessage(this.getMessage("sell.invalid-price"));
        } else if (hand == null || hand.getType() == Material.AIR) {
            player.sendMessage(this.getMessage("sell.no-item-in-hand"));
        } else {
            String itemName = hand.getType().name();
            int amount = hand.getAmount();

            this.getAPI().sell(player, hand, sellPrice);
            this.removeItemInHand(player);

            player.sendMessage(this.getMessage("sell.placed")
                .replace("%item%", itemName)
                .replace("%amount%", String.valueOf(amount))
                .replace("%price%", String.valueOf(sellPrice))
            );
        }
    }
}
