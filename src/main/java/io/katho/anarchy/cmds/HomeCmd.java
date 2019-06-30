package io.katho.anarchy.cmds;

import io.katho.anarchy.player.PlayerHomesDAO;
import io.katho.anarchy.player.PlayerHomesDAOImpl;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCmd implements CommandExecutor {

    private PlayerHomesDAO playerHomesDAO;

    public HomeCmd() {
        this.playerHomesDAO = new PlayerHomesDAOImpl();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("sethome")) {
            Player p = (Player) sender;
            if (args.length == 1) {

                if(this.playerHomesDAO.existHomes(p.getUniqueId())) {

                    this.playerHomesDAO.getHomes(p.getUniqueId()).getHomes().forEach(home -> {
                        if(home.getName().equals(args[0])) {
                            p.sendMessage("You are in your home");
                            p.teleport(home.getLoc());
                            return;
                        }
                        p.sendMessage("There are no homes with this name");
                    });

                } else {
                    p.sendMessage("You have no homes sets please use: /sethome <name>");
                    return true;
                }

            } else {
                p.sendMessage("");
                return true;
            }
        }


        return false;
    }

}
