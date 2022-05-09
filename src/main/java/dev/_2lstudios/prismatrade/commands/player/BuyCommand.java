package dev._2lstudios.prismatrade.commands.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import dev._2lstudios.prismatrade.commands.BaseCommand;

public class BuyCommand extends BaseCommand {
    public BuyCommand() {
        super("buy", 2);
    }

    public int getInteger(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {}
        return 0;
    }
    
    @Override
    public void onExecutedByPlayer(Player player, String label, String[] args) {
        Material material = Material.getMaterial(args[0].toUpperCase());
        int buyAmount = getInteger(args[1]);
        int buyPrice = getInteger(args[2]);
        
        if (material == null) {
            player.sendMessage(this.getMessage("buy.invalid-material"));
        } else if (buyAmount < 1 || buyAmount > 64) {
            player.sendMessage(this.getMessage("buy.invalid-amount"));
        } else if (buyPrice < 1) {
            player.sendMessage(this.getMessage("buy.invalid-price"));
        } else {
            this.getAPI().buy(player, material, buyAmount, buyPrice);
            player.sendMessage(this.getMessage("buy.placed")
                .replace("%item%", material.name())
                .replace("%amount%", String.valueOf(buyAmount))
                .replace("%price%", String.valueOf(buyPrice))
            );
        }
    }
}
