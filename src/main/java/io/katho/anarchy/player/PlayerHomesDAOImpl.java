package io.katho.anarchy.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.katho.anarchy.Core;

import java.io.*;
import java.util.Collection;
import java.util.UUID;

public class PlayerHomesDAOImpl implements PlayerHomesDAO {

    private Gson gson;
    private final String HOMES_DIR = Core.getInstance().getDataFolder() + "/homes/";

    public PlayerHomesDAOImpl() {
        this.gson = new GsonBuilder().create();
    }

    @Override
    public void addHomes(PlayerHomes playerHomes) {
        File dir = new File(HOMES_DIR);
        dir.mkdirs();
        try (Writer writer = new FileWriter(HOMES_DIR + playerHomes.getUuid().toString() + ".json", false)) {
            gson.toJson(playerHomes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlayerHomes getHomes(UUID uuid) {
        File dir = new File(HOMES_DIR);
        dir.mkdirs();
        try (Reader reader = new FileReader(HOMES_DIR + uuid.toString() + ".json")) {
            return gson.fromJson(reader, PlayerHomes.class);
        }  catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeHomes(UUID uuid) {
        File file = new File(HOMES_DIR + uuid.toString() + ".json");
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            try {
                throw new FileNotFoundException("The file doesn't exits or have no account on there.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (file.delete()) {
            return;
        } else {
            try {
                throw new Exception("The file isn't deleted");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateHomes(PlayerHomes playerHomes) {
        File dir = new File(HOMES_DIR);
        dir.mkdirs();
        try (Writer writer = new FileWriter(HOMES_DIR + playerHomes.getUuid().toString() + ".json")) {
            gson.toJson(playerHomes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existHomes(UUID uuid) {
        File file = new File(HOMES_DIR + uuid.toString() + ".json");
        if ((file == null) || !(file.exists())) {
            return false;
        }
        return true;
    }

    @Override
    public Collection<PlayerHomes> getAllHomes() {
        return null;
    }

}
