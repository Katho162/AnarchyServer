package io.katho.anarchy.cmds;

import io.katho.anarchy.Core;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;


public class Tpa implements CommandExecutor {

    public static Map<Player, Player> TELEPORTS;

    public Tpa() {
        TELEPORTS = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("tpa")) {
            Player p = (Player) sender;

            if (args.length == 1) {

                Player t = (Player) Bukkit.getPlayer(args[0]);
                if (t == null) {
                    p.sendMessage("&cThis player doesn't exist or is offline.");
                    return true;
                }

                if (!TELEPORTS.containsKey(p)) {

                    t.sendMessage(Core.getPluginMessages().getAsString("tpaMessage", p));
                    TextComponent msg = new TextComponent(Core.getPluginMessages().getAsString("tpaMessageQuest"));
                    msg.setColor(ChatColor.YELLOW);
                    TextComponent accept = new TextComponent(Core.getPluginMessages().getAsString("tpaAccept") + " ");
                    accept.setBold(true);
                    accept.setColor(ChatColor.GREEN);
                    accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept " + p.getName()));
                    TextComponent decline = new TextComponent(Core.getPluginMessages().getAsString("tpaDecline") + " ");
                    decline.setBold(true);
                    decline.setColor(ChatColor.DARK_RED);
                    decline.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpadecline " + p.getName()));
                    msg.addExtra(accept);
                    msg.addExtra(decline);
                    t.spigot().sendMessage(msg);
                    TELEPORTS.put(p, t);
                    p.sendMessage(Core.getPluginMessages().getAsString("tpaInvite"));

                    Bukkit.getScheduler().runTaskLaterAsynchronously(Core.getInstance(), task -> {
                        if (TELEPORTS.containsKey(p)) {
                            p.sendMessage(Core.getPluginMessages().getAsString("tpaHasDeclined", p));
                            TELEPORTS.remove(p);
                        }
                    }, 20 * 30);

                } else {
                    p.sendMessage(Core.getPluginMessages().getAsString("tpaAlready"));
                }

            }

         }

        return false;
    }
}
