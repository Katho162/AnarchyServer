package io.katho.anarchy.home;

import io.katho.anarchy.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

public class HomeDAOImpl implements HomeDAO {

    private UUID uuid;
    private HomesFile homesFile;

    public HomeDAOImpl(UUID uuid) {
        this.uuid = uuid;
        this.homesFile = new HomesFile(this.uuid, Core.getInstance());
    }

    @Override
    public void addHome(Home home) {
        String locStr = String.format("%f;%f;%f;%f;%f;%s", home.getLoc().getX(), home.getLoc().getY(), home.getLoc().getZ(), home.getLoc().getPitch(), home.getLoc().getYaw(), home.getLoc().getWorld().getName());
        this.homesFile.getFileConfiguration().set(home.getName(), locStr);
        this.homesFile.save();
    }

    @Override
    public Home getHome(String name) {
        String[] locStr = this.homesFile.getFileConfiguration().getString(name).split(";");
        World world = Bukkit.getWorld(locStr[5]);
        Location location = new Location(world, Double.parseDouble(locStr[0]), Double.parseDouble(locStr[1]), Double.parseDouble(locStr[2]), Float.parseFloat(locStr[4]), Float.parseFloat(locStr[3]));
        return new Home(location, name);
    }

    @Override
    public void removeHome(String name) throws FileNotFoundException {
        this.homesFile.getFileConfiguration().set(name, null);
        this.homesFile.save();
    }

    @Override
    public void updateHome(Home home) {
        String locStr = String.format("%f;%f;%f;%f;%f;%s", home.getLoc().getX(), home.getLoc().getY(), home.getLoc().getZ(), home.getLoc().getPitch(), home.getLoc().getYaw(), home.getLoc().getWorld().getName());
        this.homesFile.getFileConfiguration().set(home.getName(), locStr);
        this.homesFile.save();
    }

    @Override
    public boolean existHome(String name) {
        if(!(this.homesFile.getFileConfiguration().get(name) == null)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Collection<Home> getAllHomes() {
        return null;
    }
}
