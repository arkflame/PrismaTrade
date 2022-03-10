package dev._2lstudios.prismatrade.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev._2lstudios.hypermenus.HyperMenus;
import dev._2lstudios.prismatrade.PrismaTrade;

public class TradeCommand implements CommandExecutor {
    private PrismaTrade prismaTrade;
    private SellCommand sellCommand;

    public TradeCommand(PrismaTrade prismaTrade, SellCommand sellCommand) {
        this.prismaTrade = prismaTrade;
        this.sellCommand = sellCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label,
            String[] args) {
        if (args.length > 0) {
            if (args[0].equals("clearcategories")) {
                if (sender.hasPermission("trade.admin")) {
                    sender.sendMessage("Cleared categories");
                    prismaTrade.getTradeService().clearCategories();
                } else {
                    sender.sendMessage("Requires trade.admin permission");
                }
            } else if (args[0].equals("sell")) {
                sellCommand.onCommand(sender, command, label, new String[] { args.length > 0 ? args[1] : "" });
            } else {
                sender.sendMessage("Invalid argument");
            }
        } else if (sender instanceof Player) {
            Player player = (Player) sender;

            HyperMenus.getAPI().openMenu(player, prismaTrade.getMenu());
        } else {
            sender.sendMessage("Cannot use this command from the console");
        }

        return true;
    }
}
