package utils;

import com.google.gson.GsonBuilder;

public class FormatResponse {
    
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private int status;
    private String message;
    private Object data;
    //</editor-fold>
    
    private FormatResponse(
            String message, int status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Métodos">
    public static FormatResponse getErrorResponse(String msg, int state) {
        return new FormatResponse("Error request | " + msg, state, null);
    }    
    public static FormatResponse getSuccessResponse(Object data) {
        return new FormatResponse("Success request", 200, data);
    }
    public String toJsonString(GsonBuilder gsonBuilder) {
        return gsonBuilder.setPrettyPrinting().create().toJson(this);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public int getStatus() {
        return status;
    }
    //</editor-fold>

}
