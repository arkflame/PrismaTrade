package dev._2lstudios.prismatrade.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import dev._2lstudios.prismatrade.trade.TradeController;

public class SellCommand implements CommandExecutor {
    private TradeController tradeController;

    public SellCommand(TradeController tradeController) {
        this.tradeController = tradeController;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length > 0) {
                String priceText = args[0];

                try {
                    double price = Double.parseDouble(priceText);
                    Player player = (Player) sender;
                    PlayerInventory inventory = player.getInventory();
                    ItemStack item = inventory.getItem(inventory.getHeldItemSlot());

                    tradeController.sell(player, item, price);
                } catch (NumberFormatException e) {
                    sender.sendMessage("Invalid number: " + priceText);
                }
            } else {
                sender.sendMessage("/sell <price>");
            }
        } else {
            sender.sendMessage("Cannot use this command from the console");
        }

        return true;
    }
}
