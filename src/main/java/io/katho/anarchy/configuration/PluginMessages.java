package io.katho.anarchy.configuration;

import com.google.gson.JsonElement;
import org.bukkit.entity.Player;

public interface PluginMessages {

    public JsonElement get(String path);
    public String getAsString(String path);
    public String getAsString(String path, Player player);

}
