package database;

import java.sql.Connection;
import java.sql.DriverManager;
import utils.authentication.EnvEntries;

public class ConnectionDB {
    //<editor-fold defaultstate="collapsed" desc="Properties"> 
    private Connection cn;
    public String MESSAGE;
    private EnvEntries envEntries;
    //</editor-fold>
    
    public ConnectionDB() {
        cn = null;
        MESSAGE = "No response";
        envEntries = new EnvEntries();
    }

    public Connection connect() {
        try {
                
            if (cn != null && !cn.isClosed()) {
                MESSAGE = "Error: Already connected DB";
                return null;
            }
            Class.forName(envEntries.getDriverDB());
            final String URL = "jdbc:mysql://" + envEntries.getHostDB() + "/" + envEntries.getDBName();
            cn = DriverManager.getConnection(URL, envEntries.getUserDB(), envEntries.getPasswordDB());

            MESSAGE = "Connected DB";
            return cn;
        }
        catch (Exception ex) {
            MESSAGE = "Unexpected error | " + ex.getMessage();
            return null;
        } 
    }
    public boolean disconnect() {
        try {
            if (cn == null) return false;
            cn.close();
            cn = null;
            MESSAGE = "Disconnected DB";
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
