package dev._2lstudios.prismatrade;

import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.prismatrade.commands.ExampleCommand;
import dev._2lstudios.prismatrade.listeners.PlayerJoinListener;
import dev._2lstudios.prismatrade.tasks.ExampleTask;

public class PrismaTrade extends JavaPlugin {
    
    @Override
    public void onEnable () {
        // Save default config
        this.saveDefaultConfig();

        // Set static instance
        PrismaTrade.instance = this;

        // Register the example command
        this.getCommand("example").setExecutor(new ExampleCommand());
        
        // Register the example listener
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        // Register the example task
        final long taskRepeatEvery = this.getConfig().getInt("task-repeat-every") * 20L;
        this.getServer().getScheduler().runTaskTimer(this, new ExampleTask(), taskRepeatEvery, taskRepeatEvery);
    }

    private static PrismaTrade instance;

    public static PrismaTrade getInstance () {
        return PrismaTrade.instance;
    }
}