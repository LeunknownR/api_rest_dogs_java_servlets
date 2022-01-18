package controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import utils.FormatResponse;
import utils.HelperController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.authentication.JWTAuthentication;
import utils.authentication.RoleAuthJWT;

@WebServlet(name = "ControllerLogin", urlPatterns = {"/api/login"})
public class ControllerLogin extends HttpServlet {

    private JWTAuthentication jwtAuth;
    @Override
    public void init() { jwtAuth = new JWTAuthentication(); }
    @Override
    public void destroy() {  jwtAuth = null; }
    //<editor-fold defaultstate="collapsed" desc="HTTP Methods">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final JsonObject body = (JsonObject) HelperController
                .fromBodyToObject(request.getInputStream(), JsonObject.class);
        HelperController.templatePrintable(verifyAccount(body), response);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Helpers HTTP methods">
    private FormatResponse verifyAccount(final JsonObject body) {
        JsonElement user = body.get("user"), password = body.get("password");
        if (user == null || password == null) 
            return FormatResponse.getErrorResponse("Mising parameters", 400);
        else if (!"leunknownr17".equals(user.getAsString()) || 
            !"manuel1234".equals(password.getAsString()))
            return FormatResponse.getErrorResponse("User and Password doesn't match", 401);
        else {
            JsonObject objToken = new JsonObject();
            objToken.addProperty("token", jwtAuth.getToken(user.getAsString(), RoleAuthJWT.ADMIN_ROLE));
            return FormatResponse.getSuccessResponse(objToken);
        } 
    }
    //</editor-fold>

}
