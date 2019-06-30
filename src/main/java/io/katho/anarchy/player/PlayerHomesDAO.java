package io.katho.anarchy.player;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.UUID;

public interface PlayerHomesDAO {

    public void addHomes(PlayerHomes playerHomes);
    public PlayerHomes getHomes(UUID uuid);
    public void removeHomes(UUID uuid) throws FileNotFoundException;
    public void updateHomes(PlayerHomes playerHomes);
    public boolean existHomes(UUID uuid);
    public Collection<PlayerHomes> getAllHomes();

}
