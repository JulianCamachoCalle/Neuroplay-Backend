package dev.juliancamacho.neuroplay.controlador.jwt;


import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String getToken(Usuario usuario) {
        return getToken(new HashMap<>(), usuario);
    }

    private String getToken(HashMap<String,Object> extraClaims, Usuario usuario) {
        return Jwts.builder()
                .claims(extraClaims)
                .claim("id", usuario.getId())
                .claim("username", usuario.getUsername())
                .claim("nombre", usuario.getNombre())
                .claim("apellido", usuario.getApellido())
                .claim("telefono", usuario.getTelefono())
                .claim("genero", usuario.getGenero())
                .claim("estado", usuario.getEstado())
                .claim("rol", usuario.getRol())
                .subject(usuario.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails usuarioDetails) {
        final String usuarioname = getUsernameFromToken(token);
        return (usuarioname.equals(usuarioDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
