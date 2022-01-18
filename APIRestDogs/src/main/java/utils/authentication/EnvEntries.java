package utils.authentication;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnvEntries {

    private Context env;
    
    public EnvEntries() {
        try {
            env = (Context) new InitialContext().lookup("java:comp/env");
        } catch (NamingException ex) {
            env = null;
        }
    }
    //<editor-fold defaultstate="collapsed" desc="HELPER METHODS">
    private String getEntry(String key) {
        try {
            return env == null ? null : (String) env.lookup(key);
        } catch (NamingException ex) {
            return null;
        } 
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="AUTHENTICATION">
    public String getSecretKeyJWT() {
        return getEntry("SECRET_KEY");
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="DATABASE CREDENTIALS">
    public String getDBName() {
        return getEntry("DB_NAME");
    }
    public String getUserDB() {
        return getEntry("USER_DB");
    }
    public String getPasswordDB() {
        return getEntry("PASSWORD_DB");
    }
    public String getHostDB() {
        return getEntry("HOST_DB");
    }
    public String getDriverDB() {
        return getEntry("DRIVER_DB");
    }
    //</editor-fold>
}
