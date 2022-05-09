package dev._2lstudios.prismatrade.commands.player;

import org.bukkit.entity.Player;

import dev._2lstudios.prismatrade.commands.BaseCommand;
import dev._2lstudios.prismatrade.interfaces.menus.TradeMenu;

public class TradeCommand extends BaseCommand {
    public TradeCommand() {
        super("trade");
    }
    
    @Override
    public void onExecutedByPlayer(Player player, String label, String[] args) {
        TradeMenu menu = new TradeMenu(
            this.getPlugin().getMainConfig(), 
            this.getAPI()
        );
        menu.build(player);
    }
}
