package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class ModelParent {
    
    public static String MESSAGE;
    //<editor-fold defaultstate="collapsed" desc="Helper methods">    
    protected ArrayList<HashMap<String, String>> getHashMapArrayFrom(ResultSet rs) {
        try {
            final int columns = rs.getMetaData().getColumnCount();
            ArrayList<HashMap<String, String>> table = new ArrayList<>();
            
            while (rs.next()) {
                try {
                    final HashMap<String, String> row = new HashMap<>();
                    for (int i = 1; i <= columns; i++) {
                        String nameColumn = rs.getMetaData().getColumnLabel(i);
                        row.put(nameColumn, rs.getString(i));
                    }
                    table.add(row);
                }
                catch (ArrayIndexOutOfBoundsException | SQLException ex) {
                    break;
                }
            }
            return table;
        } catch (SQLException ex) {
            return null;
        }
    }
    protected HashMap<String, String> getHashMapFrom(ResultSet rs) {
        try {
            final HashMap<String, String> row = new HashMap<>();
            while (rs.next()) {
                try {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        String nameColumn = rs.getMetaData().getColumnLabel(i);
                        row.put(nameColumn, rs.getString(i));
                    }
                }
                catch (ArrayIndexOutOfBoundsException ex) {}
            }
            return row;
        } catch (SQLException ex) {
            return null;
        }
    }
    protected boolean setParameterIfNull(PreparedStatement prSt, int idxParam, int typeSQL, Object param) 
            throws SQLException {
        if (param != null) 
            return false;
        prSt.setNull(idxParam, typeSQL);
        return true;
    }
    //</editor-fold>
}
