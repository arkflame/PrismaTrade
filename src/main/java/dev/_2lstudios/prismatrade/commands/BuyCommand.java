package dev._2lstudios.prismatrade.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev._2lstudios.prismatrade.PrismaTradeAPI;

public class BuyCommand implements CommandExecutor {
    private PrismaTradeAPI prismaTrade;

    public BuyCommand(PrismaTradeAPI prismaTrade) {
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

            if (args.length > 2) {
                String materialName = args[0].toUpperCase();
                String buyAmountText = args[1];
                String buyPriceText = args[2];
                Material material = Material.getMaterial(materialName);

                if (material != null) {
                    int buyAmount = getInteger(buyAmountText);

                    if (buyAmount > 0) {
                        if (buyAmount <= 64) {
                            int buyPrice = getInteger(buyPriceText);

                            if (buyPrice > 0) {
                                prismaTrade.buy(player, material, buyAmount, buyPrice);
                            } else {
                                sender.sendMessage("Buy price is too low");
                            }
                        } else {
                            sender.sendMessage("Buy amount is too high");
                        }
                    } else {
                        sender.sendMessage("Buy amount is too low");
                    }
                } else {
                    sender.sendMessage("Material does not exist");
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
