package io.katho.anarchy.home;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface HomeDAO {

    public void addHome(Home home);
    public Home getHome(String name);
    public void removeHome(String name) throws FileNotFoundException;
    public void updateHome(Home home);
    public boolean existHome(String name);
    public ArrayList<Home> getAllHomes();

}
