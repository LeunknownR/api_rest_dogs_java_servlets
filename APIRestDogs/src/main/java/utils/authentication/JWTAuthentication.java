package utils.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;

public class JWTAuthentication {
    final String SECRET_KEY = getSecretKey();
    
    public String getToken(String username, RoleAuthJWT role) {
        final String token = Jwts
                    .builder()
                    .setSubject(username)
                    .claim("role", role.toString())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 600000))
                    .signWith(
                        SignatureAlgorithm.HS512,
                        SECRET_KEY.getBytes())
                    .compact();
        return token;
    }
    public boolean verifyToken(String token, RoleAuthJWT routeRole) {
        try {
            final JwtParser jwtParser = Jwts.parser().setSigningKey(SECRET_KEY.getBytes());
            final Claims claims = jwtParser.parseClaimsJws(token).getBody();
            final String tokenRole = claims.get("role").toString();
            return verifyRole(routeRole.toString(), tokenRole);
        }
        catch (
                ExpiredJwtException | 
                MalformedJwtException | 
                SignatureException | 
                UnsupportedJwtException | 
                IllegalArgumentException | 
                NullPointerException ex) {
            return false;
        }
    }
    private boolean verifyRole(String routeRole, String tokenRole) {
        return routeRole.equals(tokenRole);
    }
    private String getSecretKey() {
        return  new EnvEntries().getSecretKeyJWT();
    }
}
