package io.katho.anarchy.inventories;

import io.katho.anarchy.home.HomeDAO;
import io.katho.anarchy.home.HomeDAOYaml;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class HomeInvListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if(e.getView().getTopInventory().getHolder() instanceof HomeHolder) {

            e.setCancelled(true);

            if(e.getWhoClicked() instanceof Player) {

                Player p = (Player) e.getWhoClicked();

                HomeDAO homeDAO = new HomeDAOYaml(p.getUniqueId());

                if(e.getClick().isLeftClick()) {

                    p.teleport(homeDAO.getAllHomes().get(e.getSlot()).getLoc());

                }

            }

        }

    }

}
