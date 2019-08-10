package io.katho.anarchy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.*;
import io.katho.anarchy.cmds.*;
import io.katho.anarchy.configuration.PluginMessagesFactory;
import io.katho.anarchy.inventories.HomeInvListener;
import io.katho.anarchy.listeners.ChatFormater;
import io.katho.anarchy.listeners.PreLogin;
import io.katho.anarchy.configuration.PluginMessages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.UnknownHostException;
import java.util.*;

/**
 * That's the Main class of the plugin. it work like a Singleton cause the instance is
 */
public class Core extends JavaPlugin {

    // Instance of this class for future references.
    private static Plugin INSTANCE;
    // List of logged players.
    private static Set<Player> LOGGED_PLAYERS;
    private static PluginMessages PLUGIN_MESSAGES;
    // Local Instance Stuff;
    private Gson gson;
    // Mongo connection
    public static MongoClient mongoClient;
    public static DB database;

    /**
     * Is the plugin bootstrap method.
     */
    @Override
    public void onEnable() {
        INSTANCE = this;
        LOGGED_PLAYERS = new HashSet<Player>();
        this.gson = new GsonBuilder().create();
        this.buildFiles();
        PLUGIN_MESSAGES = PluginMessagesFactory.makePluginMessages();
        this.registerCommands();
        this.registerListeners();
        this.connect("localhost", 27017, "anarchy");
        Bukkit.getLogger().info("Plugin bootstrap done!");
        Bukkit.getOnlinePlayers().forEach(p -> p.kickPlayer(getPluginMessages().getAsString("reloadKick")));
    }

    /**
     * @return Plugin instance of a singleton, to prevent it from being new obejcts.
     */
    public synchronized static Plugin getInstance() {
        if (INSTANCE == null) {
            return new Core();
        }
        return INSTANCE;
    }

    /**
     * Register the plugin commands to the Plugin.
     */
    private void registerCommands() {
        getCommand("register").setExecutor(new Register());
        getCommand("login").setExecutor(new Login());
        getCommand("tpa").setExecutor(new Tpa());
        getCommand("tpaccept").setExecutor(new Tpaccept());
        getCommand("tpadecline").setExecutor(new Tpadecline());
        getCommand("sethome").setExecutor(new Sethome());
        getCommand("home").setExecutor(new HomeCmd());
        getCommand("delhome").setExecutor(new Delhome());
    }

    /**
     * Register the plugin listeners to the Plugin.
     */
    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PreLogin(), this);
        pm.registerEvents(new ChatFormater(), this);
        pm.registerEvents(new HomeInvListener(), this);
    }

    /**
     * Build main plugin configuration files.
     */
    private void buildFiles() {
        saveDefaultConfig();
        saveResource("config.json", false);
        saveResource("pt_BR.json", false);
        saveResource("en_US.json", false);
	    saveResource("cmn_CN.json", false);
	    saveResource("nb_NO.json", false);
	    saveResource("ru_RU.json", false);
	    saveResource("es_ES.json", false);
	    saveResource("de_DE.json", false);
	    saveResource("nl_NL.json", false);
	    saveResource("cs_CZ.json", false);
	    saveResource("fr_FR.json", false);
    }

    /**
     * @return The list of currently logged players.
     */
    public synchronized static Collection<Player> getLoggedIn() {
        return LOGGED_PLAYERS;
    }

    /**
     * @return The plugin messages objects. Where you can sarch for the plugin messages.
     */
    public synchronized static PluginMessages getPluginMessages() {
        return PLUGIN_MESSAGES;
    }

    /**
     * Connect to mongo database
     * @param ip the mongodb server ip
     * @param port mongodb server port
     * @param db mongodb database name
     */
    private void connect(String ip, int port, String db) {
        try {
            mongoClient = new MongoClient(ip, port);
            database = mongoClient.getDB(db);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


}
