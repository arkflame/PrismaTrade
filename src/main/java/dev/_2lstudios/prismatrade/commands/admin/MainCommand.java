package dev._2lstudios.prismatrade.commands.admin;

import org.bukkit.command.CommandSender;

import dev._2lstudios.prismatrade.commands.BaseCommand;
import net.md_5.bungee.api.ChatColor;

public class MainCommand extends BaseCommand {
    public MainCommand() {
        super("prismatrade", "prismatrade.admin");

        this.addSubCommand(new ReloadCommand());
    }

    public String getVersion() {
        return this.getPlugin().getDescription().getVersion();
    }

    @Override
    protected void onMissingPermission(CommandSender sender, String permission) {
        sender.sendMessage(ChatColor.GREEN + "PrismaTrade v" + this.getVersion() + " by LinsaFTW.");
    }
    
    @Override
    public void onExecuted(CommandSender sender, String label, String args[]) {
        sender.sendMessage(
            this.getMessage("admin.help")
                .replace("{label}", label)
                .replace("{version}", this.getVersion()
            )
        );
    }
}
