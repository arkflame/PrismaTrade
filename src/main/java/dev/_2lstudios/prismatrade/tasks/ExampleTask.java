package dev._2lstudios.prismatrade.tasks;

import org.bukkit.Bukkit;

import dev._2lstudios.prismatrade.PrismaTrade;

public class ExampleTask implements Runnable {
    @Override
    public void run() {
        final String message = PrismaTrade.getInstance().getConfig().getString("messages.from-task");
        Bukkit.getServer().broadcastMessage(message);
    }
}