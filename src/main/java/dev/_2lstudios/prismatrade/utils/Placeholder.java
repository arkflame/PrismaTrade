package dev._2lstudios.prismatrade.utils;

import java.util.List;

import com.iridium.iridiumcolorapi.IridiumColorAPI;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Placeholder {
    public static String color(String text) {
        return IridiumColorAPI.process(text);
    }

    public static String setPlaceholders(Player player, String text) {
        if (text != null) {
            if (player != null) {
                text = PlaceholderAPI.setPlaceholders(player, text);
            }

            text = color(text);
        }

        return text;
    }

    public static void sendMessage(Player player, String text) {
        player.sendMessage(setPlaceholders(player, text));
    }

    public static void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(setPlaceholders(player, title), setPlaceholders(player, subtitle), 20, 20, 20);
    }

    public static void sendAction(Player player, String text) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(text));
    }

    public static List<String> color(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, color(list.get(i)));
        }

        return list;
    }
}