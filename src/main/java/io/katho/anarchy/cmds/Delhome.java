package io.katho.anarchy.cmds;

import io.katho.anarchy.Core;
import io.katho.anarchy.home.HomeDAO;
import io.katho.anarchy.home.HomeDAOImpl;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.FileNotFoundException;

public class Delhome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("delhome")) {

            Player p = (Player) sender;

            if(args.length == 1) {

                HomeDAO homeDAO = new HomeDAOImpl(p.getUniqueId());
                if(homeDAO.existHome(args[0])) {
                    try {
                        homeDAO.removeHome(args[0]);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    p.sendMessage(Core.getPluginMessages().getAsString("homeDelete"));
                    return true;
                } else {
                    p.sendMessage(Core.getPluginMessages().getAsString("noHome"));
                    return true;
                }

            } else {
                p.sendMessage(Core.getPluginMessages().getAsString("delhomeUsage"));
                return true;
            }

        }

        return false;
    }
}
