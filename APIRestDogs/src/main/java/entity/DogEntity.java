package entity;

import com.google.gson.JsonObject;
import dto.DogDTO;
import dto.RaceDogDTO;
import dto.SizeDogDTO;
import java.io.IOException;
import model.DogModel;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import utils.delegates.DelegateVoidOneParamThrowsServletAndIOException;

public class DogEntity extends EntityParent<DogDTO> {
    
    //<editor-fold defaultstate="collapsed" desc="Action Methods">
    public ArrayList<DogDTO> getDogs(boolean all) {
        return toArrayList(new DogModel().getDogs(all));
    }
    public DogDTO findDog(final DogDTO dogDTO) {
        HashMap<String, String> row = new DogModel().findDog(dogDTO.getId());
        return row.size() <= 0 ? null : getDTOforRowHashMap(row);
    }
    public boolean addDog(final DogDTO dogDTO) {
        HashMap<String, String> row = new DogModel().addDog(dogDTO);
        return "SUCCESS".equals(row.get("RES"));
    }
    public boolean updateDog(final DogDTO dogDTO) {
        HashMap<String, String> row = new DogModel().updateDog(dogDTO);
        return "SUCCESS".equals(row.get("RES"));
    }
    public boolean carrying(final DogDTO dogDTO) {
        HashMap<String, String> row = new DogModel().carrying(dogDTO.getId());
        return "SUCCESS".equals(row.get("RES"));
    }
    //</editor-fold>
    //<editor-fold defaultstate="defaultstate" desc="Helpers">
    @Override
    protected DogDTO getDTOforRowHashMap(HashMap<String, String> row) {
        return new DogDTO(
                Integer.parseInt(row.get("ID")),
                new RaceDogDTO(
                        Integer.parseInt(row.get("ID_RACE_DOG")), 
                        row.get("RACE_DOG")),
                new SizeDogDTO(
                        Integer.parseInt(row.get("ID_SIZE_DOG")), 
                        row.get("SIZE_DOG")), 
                row.get("NAME"),
                row.get("DESCRIPTION"),
                row.get("OWNER"),
                Double.parseDouble(row.get("WEIGHT")), 
                row.get("CARRIED").equals("1")
        );
    }
    private ArrayList<DogDTO> toArrayList(ArrayList<HashMap<String, String>> table) {
        return hashMapArrayListToDTOArrayList(
                table, 
                (HashMap<String, String> row) ->  getDTOforRowHashMap(row)
        );
    }
    public String validateDogForAdd(JsonObject jObj, DogDTO dog) {
        try {
            // Validando campos
            if (isNullPropertyOfJson(jObj, "idRaceDog") ||
                    (!isNullPropertyOfJson(jObj, "idRaceDog") && 
                       !isValidPropertyValueInteger(jObj.get("idRaceDog").getAsInt(), 1, null)))
                return validateDogFromAddOrUpdateErrorMsg("idRaceDog");
            if (isNullPropertyOfJson(jObj, "idSizeDog") ||
                    (!isNullPropertyOfJson(jObj, "idSizeDog") && 
                        !isValidPropertyValueInteger(jObj.get("idSizeDog").getAsInt(), 1, null)))
                return validateDogFromAddOrUpdateErrorMsg("idSizeDog");
            if (isNullPropertyOfJson(jObj, "name") ||
                    (!isNullPropertyOfJson(jObj, "name") &&  
                        !isValidPropertyValueString(jObj.get("name").getAsString(), 1, 50)))
                return validateDogFromAddOrUpdateErrorMsg("name");
            if (isNullPropertyOfJson(jObj, "description") ||
                    (!isNullPropertyOfJson(jObj, "description") &&  
                        !isValidPropertyValueString(jObj.get("description").getAsString(), 1, 300)))
                return validateDogFromAddOrUpdateErrorMsg("description");
            if (isNullPropertyOfJson(jObj, "owner") ||
                    (!isNullPropertyOfJson(jObj, "owner") &&  
                        !isValidPropertyValueString(jObj.get("owner").getAsString(), 1, 100)))
                return validateDogFromAddOrUpdateErrorMsg("owner");
            if (isNullPropertyOfJson(jObj, "weight") ||
                    (!isNullPropertyOfJson(jObj, "weight") && 
                        !isValidPropertyValueDouble(jObj.get("weight").getAsDouble(), 1.0, null)))
                return validateDogFromAddOrUpdateErrorMsg("weight");
        }
        catch (NumberFormatException ex) {
            return "Error parsing to number | " + ex.getMessage();
        }

        // Llenando instancia dto
        dog.setRaceDog(new RaceDogDTO(jObj.get("idRaceDog").getAsInt(), ""));
        dog.setSizeDog(new SizeDogDTO(jObj.get("idSizeDog").getAsInt(), ""));
        dog.setName(jObj.get("name").getAsString());
        dog.setDescription(jObj.get("description").getAsString());
        dog.setOwner(jObj.get("owner").getAsString());
        dog.setWeight(jObj.get("weight").getAsDouble());
        return null;
    }
    public String validateDogForUpdate(JsonObject jObj, DogDTO dog) {
        // Validando campos
        int quantityNullValues = 0;
        try {
            if (isNullPropertyOfJson(jObj, "id") ||
                    (!isNullPropertyOfJson(jObj, "id") 
                    && jObj.get("id").getAsInt() <= 0))
                return validateDogFromAddOrUpdateErrorMsg("id");
            if (!isNullPropertyOfJson(jObj, "idRaceDog") && 
                    !isValidPropertyValueInteger(jObj.get("idRaceDog").getAsInt(), 1, null))
                return validateDogFromAddOrUpdateErrorMsg("idRaceDog");
            quantityNullValues = incrementQuantityNullValues("idRaceDog", jObj, quantityNullValues);
            if (!isNullPropertyOfJson(jObj, "idSizeDog") && 
                    !isValidPropertyValueInteger(jObj.get("idSizeDog").getAsInt(), 1, null))
                return validateDogFromAddOrUpdateErrorMsg("idSizeDog");
            quantityNullValues = incrementQuantityNullValues("idSizeDog", jObj, quantityNullValues);
            if (!isNullPropertyOfJson(jObj, "name") && 
                    !isValidPropertyValueString(jObj.get("name").getAsString(), 1, 50))
                return validateDogFromAddOrUpdateErrorMsg("name");
            quantityNullValues = incrementQuantityNullValues("name", jObj, quantityNullValues);
            if (!isNullPropertyOfJson(jObj, "description") && 
                    !isValidPropertyValueString(jObj.get("description").getAsString(), 1, 300))
                return validateDogFromAddOrUpdateErrorMsg("description");
            quantityNullValues = incrementQuantityNullValues("description", jObj, quantityNullValues);
            if (!isNullPropertyOfJson(jObj, "owner") && 
                    !isValidPropertyValueString(jObj.get("owner").getAsString(), 1, 100))
                return validateDogFromAddOrUpdateErrorMsg("owner");
            quantityNullValues = incrementQuantityNullValues("owner", jObj, quantityNullValues);
            if (!isNullPropertyOfJson(jObj, "weight") && 
                    !isValidPropertyValueDouble(jObj.get("weight").getAsDouble(), 1.0, null))
                return validateDogFromAddOrUpdateErrorMsg("weight");
            quantityNullValues = incrementQuantityNullValues("weight", jObj, quantityNullValues);
        }
        catch (NumberFormatException ex) {
            return "Error parsing to number | " + ex.getMessage();
        }
        // Validando si no existen parámetros
        if (quantityNullValues == 6)
            return "There is any parameters for update";
        
        // Llenando instancia dto
        dog.setId(jObj.get("id").getAsInt());
        dog.setRaceDog(new RaceDogDTO(isNullPropertyOfJson(jObj, "idRaceDog") 
                ? null : jObj.get("idRaceDog").getAsInt(), ""));
        dog.setSizeDog(new SizeDogDTO(isNullPropertyOfJson(jObj, "idSizeDog") 
                ? null : jObj.get("idSizeDog").getAsInt(), ""));
        dog.setName(isNullPropertyOfJson(jObj, "name") 
                ? null : jObj.get("name").getAsString());
        dog.setDescription(isNullPropertyOfJson(jObj, "description") 
                ? null : jObj.get("description").getAsString());
        dog.setOwner(isNullPropertyOfJson(jObj, "owner") 
                ? null : jObj.get("owner").getAsString());
        dog.setWeight(isNullPropertyOfJson(jObj, "weight") 
                ? null : jObj.get("weight").getAsDouble());
        return null;
    }
    public boolean isValidId(final String idParam, DelegateVoidOneParamThrowsServletAndIOException<String> doError) throws ServletException, IOException {
        try {
            final int id = Integer.parseInt(idParam);
            if (id <= 0)
                isValidIdErrorMessage(doError, idParam);
            return id > 0;
        }
        catch (NumberFormatException ex) {
            isValidIdErrorMessage(doError, idParam);
            return false;
        }
    }
    public boolean existsId(String idParam) {
        return idParam != null;
    }
    private boolean isValidPropertyValueString(String value, int limitDown, int limitUp) {
        final int length = value.length();
        return length >= limitDown && length <= limitUp;
    }
    private boolean isValidPropertyValueInteger(int value, Integer limitDown, Integer limitUp) {
        return value >= (limitDown != null ? limitDown : Integer.MIN_VALUE) && 
                value <= (limitUp != null ? limitUp : Integer.MAX_VALUE);
    }
    private boolean isValidPropertyValueDouble(double value, Double limitDown, Double limitUp) {
        return value >= (limitDown != null ? limitDown : Double.MIN_VALUE) && 
                value <= (limitUp != null ? limitUp : Double.MAX_VALUE);
    }
    private int incrementQuantityNullValues(String key, JsonObject jObj, int previousValue) {
        return isNullPropertyOfJson(jObj, key) ? previousValue + 1 : previousValue;
    }
    private void isValidIdErrorMessage(DelegateVoidOneParamThrowsServletAndIOException<String> doError, String idParam) throws ServletException, IOException {
        doError.Execute("Id " +idParam +" is not valid number");
    }
    private String validateDogFromAddOrUpdateErrorMsg(final String noValidParameterName) {
        return "The " + noValidParameterName + " is not a valid parameter.";
    }
    //</editor-fold>
    
}