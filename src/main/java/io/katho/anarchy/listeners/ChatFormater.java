package io.katho.anarchy.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormater implements Listener {

    @EventHandler
    public void chatFormatter(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();
        e.setFormat(ChatColor.GRAY + p.getDisplayName() + ChatColor.DARK_GRAY + " | " + ChatColor.WHITE + msg);
    }

}
