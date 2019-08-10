package io.katho.anarchy.inventories;

import io.katho.anarchy.home.HomeDAO;
import io.katho.anarchy.home.HomeDAOYaml;
import io.katho.anarchy.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class HomeHolder implements InventoryHolder {

    private String title;
    private Player player;
    private Inventory inv;
    private HomeDAO homeDAO;

    public HomeHolder(Player player) {
        this.player = player;
        this.homeDAO = new HomeDAOYaml(this.player.getUniqueId());
        this.title = player.getName() + "'s homes";
        this.inv = Bukkit.createInventory(this, 9*3, this.title);
        // Place homes
        this.homeDAO.getAllHomes().forEach(h -> {
            String homeName = ChatColor.RESET + h.getName();
            Location homeLoc = h.getLoc();
            String x = ChatColor.DARK_GRAY + "X: " + homeLoc.getX();
            String y = ChatColor.DARK_GRAY + "Y: " + homeLoc.getY();
            String z = ChatColor.DARK_GRAY + "Z: " + homeLoc.getZ();
            ItemStack icon = new ItemBuilder(Material.CHEST).setTitle(homeName).setLore(x, y, z).build();
            this.inv.addItem(icon);
        });

    }

    @Override
    public Inventory getInventory() {
        return this.inv;
    }
}
