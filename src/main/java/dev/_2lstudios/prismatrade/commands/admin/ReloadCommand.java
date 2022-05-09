package dev._2lstudios.prismatrade.commands.admin;

import org.bukkit.command.CommandSender;

import dev._2lstudios.prismatrade.commands.BaseCommand;


public class ReloadCommand extends BaseCommand {
    /**
     * This class is a child subcommand for this parent command:
     * {@link dev._2lstudios.prismatrade.commands.admin.MainCommand}
     */
    public ReloadCommand() {
        super("reload");
    }
    
    @Override
    public void onExecuted(CommandSender sender, String label, String[] args) {
        this.getPlugin().reload();
        sender.sendMessage(this.getMessage("admin.reloaded"));
    }
}
