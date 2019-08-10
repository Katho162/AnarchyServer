package io.katho.anarchy.player;

import com.mongodb.*;
import io.katho.anarchy.Core;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.UUID;

public class PlayerAccountDAOMongo implements PlayerAccountDAO {

    private DBCollection collection;

    public PlayerAccountDAOMongo() {
        this.collection = Core.database.getCollection("accounts");
    }

    @Override
    public void addAccount(PlayerAccount playerAccount) {
        DBObject obj = new BasicDBObject("uuid", playerAccount.getUUID().toString());
        obj.put("name", playerAccount.getName());
        obj.put("lastIP", playerAccount.getLastIP());
        obj.put("password", playerAccount.getPassword());
        obj.put("lastLogin", playerAccount.getLastLogin());
        obj.put("registerTimestamp", playerAccount.getRegisterTimestamp());
        this.collection.insert(obj);
    }

    @Override
    public PlayerAccount getAccount(UUID uuid) {
        DBObject match = new BasicDBObject("uuid", uuid.toString());
        DBObject obj = this.collection.findOne(match);
        UUID dbUuid = UUID.fromString(((String) obj.get("uuid")));
        String name = (String) obj.get("name");
        String lastIP = (String) obj.get("lastIP");
        String password = (String) obj.get("password");
        long lastLogin = (long) obj.get("lastLogin");
        long registerTimestamp = (long) obj.get("registerTimestamp");
        PlayerAccount account = new PlayerAccount(dbUuid, name, lastIP, password, lastLogin, registerTimestamp);
        return account;
    }

    @Override
    public void removeAccount(UUID uuid) {
        DBObject match = new BasicDBObject("uuid", uuid.toString());
        DBObject obj = this.collection.findOne(match);
        WriteResult write = this.collection.remove(obj);
    }

    public void updateAccount(PlayerAccount oldAccount, PlayerAccount newAccount) {
        DBObject match = new BasicDBObject("uuid", oldAccount.getUUID().toString());
        DBObject obj = this.collection.findOne(match);
        DBObject newObject = new BasicDBObject("uuid", newAccount.getUUID().toString());
        obj.put("name", newAccount.getName());
        obj.put("lastIP", newAccount.getLastIP());
        obj.put("password", newAccount.getPassword());
        obj.put("lastLogin", newAccount.getLastLogin());
        obj.put("registerTimestamp", newAccount.getRegisterTimestamp());
        this.collection.update(obj, newObject);
    }

    @Override
    public boolean existAccount(UUID uuid) {
        DBObject match = new BasicDBObject("uuid", uuid.toString());
        DBObject obj = this.collection.findOne(match);
        if(obj == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Collection<PlayerAccount> getAllAccounts() {
        return null;
    }
}
