package dev._2lstudios.prismatrade.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import dev._2lstudios.prismatrade.PrismaTradeAPI;
import dev._2lstudios.prismatrade.interfaces.menus.TradeMenu;

public class TradeCommand implements CommandExecutor {
    private Configuration config;
    private PrismaTradeAPI prismaTrade;

    public TradeCommand(Configuration config, PrismaTradeAPI prismaTrade) {
        this.config = config;
        this.prismaTrade = prismaTrade;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            new TradeMenu(config, prismaTrade).build(player);
        } else {
            sender.sendMessage("Not a player");
        }

        return true;
    }
}
