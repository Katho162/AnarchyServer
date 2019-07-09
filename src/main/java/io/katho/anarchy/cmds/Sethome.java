package io.katho.anarchy.cmds;

import io.katho.anarchy.Core;
import io.katho.anarchy.home.Home;
import io.katho.anarchy.home.HomeDAO;
import io.katho.anarchy.home.HomeDAOImpl;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sethome implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("sethome")) {
            Player p = (Player) sender;
            if (args.length == 1) {

                HomeDAO homeDAO = new HomeDAOImpl(p.getUniqueId());

                if(homeDAO.existHome(args[0])) {

                    Home home = new Home(p.getLocation(), args[0]);
                    homeDAO.updateHome(home);
                    p.sendMessage(Core.getPluginMessages().getAsString("homeUpdate"));

                } else {

                    Home home = new Home(p.getLocation(), args[0]);
                    homeDAO.addHome(home);
                    p.sendMessage(Core.getPluginMessages().getAsString("homeCreate"));

                }


            } else {
                p.sendMessage(Core.getPluginMessages().getAsString("sethomeUsage"));
                return true;
            }
        }


        return false;
    }
}
