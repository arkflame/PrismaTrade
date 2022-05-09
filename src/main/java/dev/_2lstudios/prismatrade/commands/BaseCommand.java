package dev._2lstudios.prismatrade.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev._2lstudios.prismatrade.PrismaTrade;
import dev._2lstudios.prismatrade.PrismaTradeAPI;

public class BaseCommand implements CommandExecutor {
    protected String name;
    private String permission;
    private int minArguments;

    private Map<String, BaseCommand> subcommands;
    protected PrismaTrade plugin;

    public BaseCommand(String name, String permission, int minArguments) {
        this.name = name;
        this.permission = permission;
        this.minArguments = minArguments;

        this.subcommands = new HashMap<>();
    }

    public BaseCommand(String name, String permission) {
        this(name, permission, 0);
    }

    public BaseCommand(String name, int minArguments) {
        this(name, null, minArguments);
    }

    public BaseCommand(String name) {
        this(name, null, 0);
    }

    public String getName() {
        return this.name;
    }

    public void addSubCommand(BaseCommand subcommand) {
        this.subcommands.put(subcommand.getName(), subcommand);
    }

    public boolean hasSubCommand(String name) {
        return this.subcommands.containsKey(name.toLowerCase());
    }

    public BaseCommand getSubCommand(String name) {
        return this.subcommands.get(name.toLowerCase());
    }

    public void register(PrismaTrade plugin) {
        this.plugin = plugin;
        plugin.getCommand(this.name).setExecutor(this);

        for (BaseCommand command : this.subcommands.values()) {
            command.plugin = this.plugin;
        }
    }

    public PrismaTradeAPI getAPI() {
        return PrismaTrade.getAPI();
    }

    public PrismaTrade getPlugin() {
        return this.plugin;
    }

    public String getRawMessage(String path) {
        String message = this.plugin.getMessages().getString(path);
        return message != null ? message : "<Missing translation key " + path + ">";
    }

    public String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', this.getRawMessage(path));
    }

    // Default methods to override
    protected void onMissingPermission(CommandSender sender, String permission) {
        sender.sendMessage(this.getMessage("common.no-permission").replace("{permission}", this.permission));
    }

    protected void onBadUsage(CommandSender sender, String label) {
        sender.sendMessage(this.getMessage(this.name + ".usage").replace("{label}", label));
    }

    // Command handler
    protected void process(CommandSender sender, Command command, String label, String[] args) {
        // Check for permissions.
        if (this.permission != null && !sender.hasPermission(this.permission)) {
            this.onMissingPermission(sender, permission);
        }

        // Check for subcommands.
        else if (args.length > 0 && this.hasSubCommand(args[0])) {
            BaseCommand subcommand = this.getSubCommand(args[0]);
            String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
            String newLabel = label + " " + subcommand.getName();
            subcommand.process(sender, command, newLabel, newArgs);
        }

        // Check for min arguments.
        else if (args.length < minArguments) {
            this.onBadUsage(sender, label);
        }

        // Otherwise, run the command.
        else {
            this.onExecuted(sender, label, args);
        }
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.process(sender, command, label, args);
        return true;
    }

    public void onExecutedByPlayer(Player player, String label, String[] args) {
        player.sendMessage(ChatColor.RED + "Not implemented command " + this.name + " in class " + this.getClass().getName());
    }

    public void onExecuted(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            this.onExecutedByPlayer((Player) sender, label, args);
        } else {
            sender.sendMessage(this.getMessage("common.not-in-console"));
        }
    }
}
