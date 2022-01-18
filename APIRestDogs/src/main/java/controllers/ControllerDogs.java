package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import utils.FormatResponse;
import utils.HelperController;
import dto.DogDTO;
import entity.DogEntity;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DogModel;

@WebServlet(name = "ControllerDogs", urlPatterns = {"/api/dogs"})
public class ControllerDogs extends HttpServlet {
    
    //<editor-fold defaultstate="collapsed" desc="HTTP Methods">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        final DogEntity dogEntity = new DogEntity();
        if (getOnlyOneDog(dogEntity, request, response)) return;
        getAllDogs(dogEntity, request.getParameter("all"), response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="body">
        /*
        {
            "idRaceDog": 6,
            "idSizeDog: 5, 
            "name": "Goku",
            "description": "Es un perro mutante.",
            "owner": "Manuel Rivera Becerra",
            "weight": 123.0
        }
        */
        //</editor-fold>
        final DogEntity dogEntity = new DogEntity();
        addDog(dogEntity, request, response); 
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="body">
        /*
        {
            "id": 2,
            "idRaceDog": 6,
            "idSizeDog: 5, 
            "name": "Goku",
            "description": "Es un perro mutante.",
            "owner": "Manuel Rivera Becerra",
            "weight": 123.0
        }
        */
        //</editor-fold>
        final DogEntity dogEntity = new DogEntity();
        updateDog(dogEntity, request, response); 
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        final DogEntity dogEntity = new DogEntity();
        carryingDog(dogEntity, request, response);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Helpers HTTP methods">
    private void getAllDogs(
            final DogEntity dogEntity,
            final String all, 
            final HttpServletResponse res) 
        throws ServletException, IOException {
        try {
            ArrayList<DogDTO> dogs = dogEntity.getDogs(Boolean.parseBoolean(all));
            HelperController.templatePrintable(
                    FormatResponse.getSuccessResponse(dogs), res);
        } catch (IOException | ServletException ex) {
            HelperController.templatePrintable(
                    FormatResponse.getErrorResponse(ex.getMessage(), 400), res);
        }
    }
    private boolean getOnlyOneDog(
            final DogEntity dogEntity, 
            final HttpServletRequest req, final HttpServletResponse res) 
        throws ServletException, IOException {
        
        final String idParam = req.getParameter("id");
        // Validando si existe el parametro
        if (dogEntity.existsId(idParam)) {
            // Validado si el parámetro es un valor válido
            if (!dogEntity.isValidId(idParam, (msg) -> {
                HelperController.templatePrintable(                
                        FormatResponse.getErrorResponse(msg, 400), res);
            })) 
               return true;
            
            final DogDTO dogIn = new DogDTO();
            dogIn.setId(Integer.parseInt(idParam));
            // Buscando perro
            final DogDTO dogFound = dogEntity.findDog(dogIn);
            // Si no existe el perro
            final FormatResponse formatResponse = 
                    (dogFound == null) 
                    ? FormatResponse.getErrorResponse("The dog doesn't exist", 400) 
                    : FormatResponse.getSuccessResponse(dogFound);
            // Imprimiendo respuesta
            HelperController.templatePrintable(formatResponse, res);
            return true;
        }        
        return false;
    }
    private void addDog(
            final DogEntity dogEntity,
            final HttpServletRequest req, 
            final HttpServletResponse res) throws ServletException, IOException {
        try {
            // Convirtiendo a DogDTO y validando parámetros de entrada
            final JsonObject body = getRequestBody(req);
            if (body == null) {
                HelperController.templatePrintable(
                    FormatResponse.getErrorResponse("The request body doesn't have json format", 400), res);
                return;
            }
            final DogDTO newDog = new DogDTO();
            final String msgError = dogEntity.validateDogForAdd(body, newDog);
            if (msgError != null) 
            {
                HelperController.templatePrintable(
                        FormatResponse.getErrorResponse(msgError, 400), res);
                return;
            }
            final boolean success = dogEntity.addDog(newDog);
            HelperController.templatePrintable(
                    success 
                    ? FormatResponse.getSuccessResponse(dogEntity.getDogs(false))
                    : FormatResponse.getErrorResponse("Unexpected error to add dog", 400), res);
        }
        catch (Exception ex) {
            HelperController.templatePrintable(
                    FormatResponse.getErrorResponse(
                            ex.getMessage() + " | " + DogModel.MESSAGE, 400), res);
        }
    }
    private void updateDog(
            final DogEntity dogEntity,
            final HttpServletRequest req, 
            final HttpServletResponse res) throws ServletException, IOException {
        try {
            // Convirtiendo a DogDTO y validando parámetros de entrada
            final JsonObject body = getRequestBody(req);
            if (body == null) {
                HelperController.templatePrintable(
                    FormatResponse.getErrorResponse("The request body doesn't have json format", 400), res);
                return;
            }
            final DogDTO newDog = new DogDTO();
            String msgError = dogEntity.validateDogForUpdate(body, newDog);
            if (msgError != null) 
            {
                HelperController.templatePrintable(
                        FormatResponse.getErrorResponse(msgError, 400), res);
                return;
            }
            final boolean success = dogEntity.updateDog(newDog);
            HelperController.templatePrintable(
                    success 
                    ? FormatResponse.getSuccessResponse(dogEntity.getDogs(false))
                    : FormatResponse.getErrorResponse("Unexpected error to update dog", 400), res);
            HelperController.templatePrintable(FormatResponse.getSuccessResponse(newDog), res);
        }
        catch (Exception ex) {
            HelperController.templatePrintable(
                    FormatResponse.getErrorResponse(
                            ex.getMessage() + " | " + DogModel.MESSAGE, 400), res);
        }
    }
    private void carryingDog(DogEntity dogEntity, 
            final HttpServletRequest req, final HttpServletResponse res) 
            throws ServletException, IOException {
        try {
            // Convirtiendo a DogDTO y validando parámetros de entrada
            final JsonObject body = getRequestBody(req);
            if (body == null) {
                HelperController.templatePrintable(
                    FormatResponse.getErrorResponse("The request body doesn't have json format", 400), res);
                return;
            }
            //Validando que el id exista
            if (dogEntity.isNullPropertyOfJson(body, "id"))
            {
                HelperController.templatePrintable(
                    FormatResponse.getErrorResponse("The request body doesn't have the property id", 400), res);
                return;
            }
            if (!dogEntity.isValidId(body.get("id").getAsString(), (msg) -> {
                HelperController.templatePrintable(                
                        FormatResponse.getErrorResponse(msg, 400), res);
            }))
               return;
            
            final DogDTO newDog = new DogDTO();
            newDog.setId(body.get("id").getAsInt());
            
            final boolean success = dogEntity.carrying(newDog);
            HelperController.templatePrintable(
                    success 
                    ? FormatResponse.getSuccessResponse(dogEntity.getDogs(false))
                    : FormatResponse.getErrorResponse("Unexpected error to carrying dog", 400), res);
        }
        catch (Exception ex) {
            HelperController.templatePrintable(
                    FormatResponse.getErrorResponse(
                            ex.getMessage() + " | " + DogModel.MESSAGE, 400), res);
        }
    }
    
    
    private JsonObject getRequestBody(final HttpServletRequest req) {
        try {
            // Convirtiendo a DogDTO y validando parámetros de entrada
            return (JsonObject) HelperController
                    .fromBodyToObject(req.getInputStream(), JsonObject.class);
        } catch (Exception ex) {
            return null;
        }
    }
    //</editor-fold>
    
}
