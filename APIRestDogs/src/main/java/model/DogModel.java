package model;

import database.ConnectionDB;
import database.ProceduresDB;
import dto.DogDTO;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class DogModel extends ModelParent {
    
    //<editor-fold defaultstate="default" desc="Access Methods">
    public ArrayList<HashMap<String, String>> getDogs(boolean all) {
        final ConnectionDB cnDB = new ConnectionDB();
        final Connection cnObj = cnDB.connect();
        try {
            final PreparedStatement prSt = cnObj.prepareStatement(ProceduresDB.GET_DOGS);
            prSt.setBoolean(1, all);
            final ResultSet rs = prSt.executeQuery();
            return getHashMapArrayFrom(rs);
        } catch (SQLException ex) {
            MESSAGE = ex.getMessage();
            return null;
        }
        finally {
            cnDB.disconnect();
        }
    }
    public HashMap<String, String> findDog(final int id) {
        final ConnectionDB cnDB = new ConnectionDB();
        final Connection cnObj = cnDB.connect();
        try {
            final PreparedStatement prSt = cnObj.prepareStatement(ProceduresDB.FIND_DOG);
            prSt.setInt(1, id);
            final ResultSet rs = prSt.executeQuery();
            return getHashMapFrom(rs);
        }
        catch (SQLException ex) {
            MESSAGE = ex.getMessage();
            return null;
        }
        finally {
            cnDB.disconnect();
        }
    }
    public HashMap<String, String> addDog(final DogDTO dog) {
        final ConnectionDB cnDB = new ConnectionDB();
        final Connection cnObj = cnDB.connect();
        try {
            final PreparedStatement prSt = cnObj.prepareStatement(ProceduresDB.ADD_DOG);
            prSt.setString(1, dog.getName());
            prSt.setDouble(2, dog.getWeight());
            prSt.setInt(3, dog.getRaceDog().getId());
            prSt.setInt(4, dog.getSizeDog().getId());
            prSt.setString(5, dog.getOwner());
            prSt.setString(6, dog.getDescription());

            final ResultSet rs = prSt.executeQuery();
            return getHashMapFrom(rs);
        }
        catch (SQLException ex) {
            MESSAGE = ex.getMessage();
            return null;
        }
        finally {
            cnDB.disconnect();
        }
    }
    public HashMap<String, String> updateDog(final DogDTO dog) {
        final ConnectionDB cnDB = new ConnectionDB();
        final Connection cnObj = cnDB.connect();
        try {
            final PreparedStatement prSt = cnObj.prepareStatement(ProceduresDB.UPDATE_DOG);
            prSt.setInt(1, dog.getId());
            if (!setParameterIfNull(prSt, 2, Types.VARCHAR, dog.getName()))
                prSt.setString(2, dog.getName());
            if (!setParameterIfNull(prSt, 3, Types.DECIMAL, dog.getWeight()))
                prSt.setDouble(3, dog.getWeight());
            if (!setParameterIfNull(prSt, 4, Types.INTEGER, dog.getRaceDog().getId()))
                prSt.setDouble(4, dog.getRaceDog().getId());
            if (!setParameterIfNull(prSt, 5, Types.INTEGER, dog.getSizeDog().getId()))
                prSt.setDouble(5, dog.getSizeDog().getId());
            if (!setParameterIfNull(prSt, 6, Types.VARCHAR, dog.getOwner()))
                prSt.setString(6, dog.getOwner());
            if (!setParameterIfNull(prSt, 7, Types.VARCHAR, dog.getDescription()))
                prSt.setString(7, dog.getDescription());

            final ResultSet rs = prSt.executeQuery();
            return getHashMapFrom(rs);
        }
        catch (SQLException ex) {
            MESSAGE = ex.getMessage();
            return null;
        }
        finally {
            cnDB.disconnect();
        }
    }
    public HashMap<String, String> carrying(final int id) {
        final ConnectionDB cnDB = new ConnectionDB();
        final Connection cnObj = cnDB.connect();
        try {
            final PreparedStatement prSt = cnObj.prepareStatement(ProceduresDB.CARRY_DOG);
            prSt.setInt(1, id);

            final ResultSet rs = prSt.executeQuery();
            return getHashMapFrom(rs);
        }
        catch (SQLException ex) {
            MESSAGE = ex.getMessage();
            return null;
        }
        finally {
            cnDB.disconnect();
        }
    }
    //</editor-fold>
    
}
