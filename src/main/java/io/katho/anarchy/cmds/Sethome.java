package io.katho.anarchy.cmds;

import io.katho.anarchy.player.Home;
import io.katho.anarchy.player.PlayerHomes;
import io.katho.anarchy.player.PlayerHomesDAO;
import io.katho.anarchy.player.PlayerHomesDAOImpl;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Sethome implements CommandExecutor {

    private PlayerHomesDAO playerHomesDAO;

    public Sethome() {
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

                    Home home = new Home(p.getLocation(), args[0]);
                    PlayerHomes homes = this.playerHomesDAO.getHomes(p.getUniqueId());
                    ArrayList<Home> homesList = homes.getHomes();
                    homesList.add(home);
                    homes.setHomes(homesList);
                    this.playerHomesDAO.updateHomes(homes);
                    p.sendMessage("Home set.");
                    return true;

                } else {

                    Home home = new Home(p.getLocation(), args[0]);
                    ArrayList<Home> homesList = new ArrayList<Home>();
                    homesList.add(home);
                    PlayerHomes homes = new PlayerHomes(p.getUniqueId(), homesList);
                    playerHomesDAO.addHomes(homes);
                    p.sendMessage("Home set.");
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
