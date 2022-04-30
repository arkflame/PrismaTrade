package dev._2lstudios.prismatrade.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import dev._2lstudios.prismatrade.PrismaTradeAPI;

public class SellCommand implements CommandExecutor {
    private PrismaTradeAPI prismaTrade;

    public SellCommand(PrismaTradeAPI prismaTrade) {
        this.prismaTrade = prismaTrade;
    }

    private int getInteger(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            // Ignored
        }

        return 0;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length > 0) {
                PlayerInventory inventory = player.getInventory();
                int heldItemSlot = inventory.getHeldItemSlot();
                ItemStack item = inventory.getItem(heldItemSlot);

                inventory.setItem(heldItemSlot, null);

                if (item != null && item.getType() != Material.AIR) {
                    String sellPriceText = args[0];
                    int sellPrice = getInteger(sellPriceText);

                    if (sellPrice > 0) {
                        prismaTrade.sell(player, item, sellPrice);
                    } else {
                        sender.sendMessage("Sell price is less than 0");
                    }
                } else {
                    sender.sendMessage("You need an item in hand");
                }
            } else {
                sender.sendMessage("Not enough arguments");
            }
        } else {
            sender.sendMessage("Not a player");
        }

        return true;
    }
}
