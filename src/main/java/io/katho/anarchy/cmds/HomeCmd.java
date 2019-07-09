package io.katho.anarchy.cmds;

import io.katho.anarchy.Core;
import io.katho.anarchy.home.HomeDAO;
import io.katho.anarchy.home.HomeDAOImpl;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("home")) {
            Player p = (Player) sender;
            if (args.length == 1) {

                HomeDAO homeDAO = new HomeDAOImpl(p.getUniqueId());

                if(homeDAO.existHome(args[0])) {

                    p.teleport(homeDAO.getHome(args[0]).getLoc());
                    p.sendMessage(Core.getPluginMessages().getAsString("homeTeleport"));
                    return true;
                } else {
                    p.sendMessage(Core.getPluginMessages().getAsString("noHome"));
                    return true;
                }

            } else {
                p.sendMessage(Core.getPluginMessages().getAsString("homeUsage"));
                return true;
            }
        }


        return false;
    }

}
