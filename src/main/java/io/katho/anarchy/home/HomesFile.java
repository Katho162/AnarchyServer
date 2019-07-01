package io.katho.anarchy.home;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class HomeConfig {

    private Plugin instance;
    private File folder;
    private File file;
    private FileConfiguration fileConfiguration;

    public HomeConfig(UUID uuid, Plugin instance) {
        this.instance = instance;
        this.folder = new File(instance.getDataFolder(), "homes");
        this.file = new File(folder, uuid.toString());
    }

    public FileConfiguration getFileConfiguration() {
        if (fileConfiguration == null) {
            reload();
        }
        return fileConfiguration;
    }

    public void reload() {
        if(!folder.exists()) {
            folder.mkdir();
        }
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        if(fileConfiguration == null || file == null) {
            return;
        }
        try {
            getFileConfiguration().save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
