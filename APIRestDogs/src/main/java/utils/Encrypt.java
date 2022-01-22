package utils;

import org.mindrot.jbcrypt.BCrypt;

public class Encrypt {
    public static final String PEPPER_PASSWORD_ACCOUNT = "soyUnPimientoXd";

    public static String doEncrypt(String value) {
        try {
            return BCrypt.hashpw(
                    value,  
                    BCrypt.gensalt() + PEPPER_PASSWORD_ACCOUNT);
        }
        catch (Exception ex) {
            return null;
        }
    }
    public static boolean matchWithHashedValue(
            final String candidate, final String hashedValue) {
        try {
            return BCrypt.checkpw(candidate, hashedValue);
        }
        catch (Exception ex) {
            return false;
        }
    }
}
