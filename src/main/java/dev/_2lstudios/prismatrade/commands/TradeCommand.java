package dev._2lstudios.prismatrade.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev._2lstudios.prismatrade.PrismaTradeAPI;
import dev._2lstudios.prismatrade.entities.BuyOrder;
import dev._2lstudios.prismatrade.entities.SellOrder;

public class TradeCommand implements CommandExecutor {
    private PrismaTradeAPI prismaTrade;

    public TradeCommand(PrismaTradeAPI prismaTrade) {
        this.prismaTrade = prismaTrade;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Buy Orders");

        for (BuyOrder buyOrder : prismaTrade.getBuyOrders(null, 10, 0)) {
            sender.sendMessage(buyOrder.getOwnerName() + " - " + buyOrder.getMaterial() + " price: "
                    + buyOrder.getPrice() + " amount: " + buyOrder.getAmount());
        }

        sender.sendMessage("Sell Orders");

        for (SellOrder sellOrder : prismaTrade.getSellOrders(null, 10, 0)) {
            sender.sendMessage(sellOrder.getOwnerName() + " - " + sellOrder.getMaterial() + " price: "
                    + sellOrder.getPrice() + " amount: " + sellOrder.getAmount());
        }

        return true;
    }
}
