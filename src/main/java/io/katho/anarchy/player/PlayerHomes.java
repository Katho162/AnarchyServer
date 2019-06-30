package io.katho.anarchy.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerHomes {

    private UUID uuid;
    private ArrayList<Home> homes;

    public PlayerHomes(UUID uuid, ArrayList<Home> homes) {
        this.uuid = uuid;
        this.homes = homes;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ArrayList<Home> getHomes() {
        return homes;
    }

    public void setHomes(ArrayList<Home> homes) {
        this.homes = homes;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
