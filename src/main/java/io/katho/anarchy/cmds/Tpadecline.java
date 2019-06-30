package io.katho.anarchy.cmds;

import io.katho.anarchy.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpadecline implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("tpadecline")) {
            Player p = (Player) sender;

            if (args.length == 0) {

                for (Player origin : Tpa.TELEPORTS.keySet()) {
                    if (Tpa.TELEPORTS.containsKey(origin)) {
                        if (Tpa.TELEPORTS.get(origin).equals(p)) {
                            origin.sendMessage(Core.getPluginMessages().getAsString("tpaHasDeclined", Tpa.TELEPORTS.get(origin)));
                            Tpa.TELEPORTS.remove(origin);
                            return true;
                        }
                    }
                }

                p.sendMessage(Core.getPluginMessages().getAsString("noTpa"));
                return true;

            } else if (args.length == 1) {

                Player t = Bukkit.getPlayer(args[0]);
                if (t == null) {
                    p.sendMessage(Core.getPluginMessages().getAsString("playerNotOnline"));
                }

                if (Tpa.TELEPORTS.containsKey(t)) {
                    t.sendMessage(Core.getPluginMessages().getAsString("tpaHasDeclined", t));
                    Tpa.TELEPORTS.remove(t);
                    p.sendMessage(Core.getPluginMessages().getAsString("tpaDecline"));
                    return true;
                } else {
                    p.sendMessage(Core.getPluginMessages().getAsString("tpaInvalid"));
                    return true;
                }

            } else {
                p.sendMessage(Core.getPluginMessages().getAsString("tpadeclineUsage"));
                return true;
            }


        }

        return false;
    }

}
