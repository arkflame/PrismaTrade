package dev._2lstudios.prismatrade.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev._2lstudios.hypermenus.HyperMenus;
import dev._2lstudios.prismatrade.menus.PrismaTradeMenu;

public class TradeCommand implements CommandExecutor {
    private final PrismaTradeMenu prismaTradeMenu = new PrismaTradeMenu();

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label,
            final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;

            HyperMenus.getAPI().openMenu(player, prismaTradeMenu);
        } else {

        }

        return true;
    }
}
