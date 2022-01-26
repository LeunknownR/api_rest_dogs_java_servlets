package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelperController {

    // <editor-fold defaultstate="collapsed" desc="Methods">
    public static void templatePrintable(FormatResponse formatJsonResponse, HttpServletResponse res) 
            throws ServletException, IOException {
        SetStatusResponse(res, formatJsonResponse.getStatus());
        // Agregando headers de autorización
        res.addHeader("Access-Control-Allow-Origin", "http://localhost:80");
        // Estableciendo contenido a responder
        res.setContentType("application/json");
        // Respondiendo json body
        final PrintWriter out = res.getWriter();
        out.println(formatJsonResponse.toJsonString(new GsonBuilder())); 
        out.close();
    }
    public static Object fromBodyToObject(final ServletInputStream servInpStream, Class typeClass) {
        return new Gson()
                .fromJson(new BufferedReader(
                        new InputStreamReader(
                                servInpStream, 
                                StandardCharsets.UTF_8)), typeClass);
    }
    public static JsonObject getRequestBody(final HttpServletRequest req) {
        try {
            // Convirtiendo a DogDTO y validando parámetros de entrada
            return (JsonObject) HelperController
                    .fromBodyToObject(req.getInputStream(), JsonObject.class);
        } catch (Exception ex) {
            return null;
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Helpers">
    private static void SetStatusResponse(HttpServletResponse res, int status) {
        switch (status) {
            case 401: 
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                break;
            case 400: 
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                break;
            default:
                res.setStatus(HttpServletResponse.SC_OK);
                break;
        }
    }
    // </editor-fold>
    
}
