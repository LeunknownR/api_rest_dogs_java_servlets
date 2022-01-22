package controllers;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.annotation.WebFilter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.FormatResponse;
import utils.HelperController;
import utils.authentication.JWTAuthentication;
import utils.authentication.RoleAuthJWT;

@WebFilter (filterName="FilterAuthentication", urlPatterns = {"/api/*"})
public class FilterAuthentication implements Filter {
    
    private JWTAuthentication jwtAuth;
    @Override
    public void init(FilterConfig filterConfig) 
            throws ServletException { jwtAuth = new JWTAuthentication(); }

    @Override
    public void doFilter(ServletRequest request, 
            ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        // Obteniendo ruta actual
        final String currentPath = ((HttpServletRequest) request).getRequestURI();
        // Continuar si no son las rutas controladores o en la ruta de login
        if (!isPathController(currentPath) || isInThisPath(currentPath, "/login")) {
            chain.doFilter(request, response);
            return;
        }
        final String token = ((HttpServletRequest) request).getHeader("Authorization");
        if (isValidToken(token)) {
            chain.doFilter(request, response);
            return;
        }
        // Restringiendo y respondiendo en caso no haya token
        HelperController.templatePrintable(FormatResponse.getErrorResponse("Unauthorized", 401), (HttpServletResponse) response);
    }

    @Override
    public void destroy() { jwtAuth = null; }
    private boolean isValidToken(String token) {
        if (token == null || !token.startsWith("bearer ")) 
            return false;
        String tokenWithoutBearer = token.split("bearer ")[1].trim();
        return jwtAuth.verifyToken(tokenWithoutBearer, RoleAuthJWT.ADMIN_ROLE);
    }
    private boolean isPathController(final String currentPath) {
        return Arrays.stream(ControllerPatterns.PATHS).anyMatch(PATH -> currentPath.equals("/TestAPIRestServlet/api" + PATH));
    }
    private boolean isInThisPath(final String currentPath, final String thisPath) {
        return currentPath.equals("/TestAPIRestServlet/api" + thisPath);
    }
}
