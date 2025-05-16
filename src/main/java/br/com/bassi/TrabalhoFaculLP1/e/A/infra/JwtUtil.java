package br.com.bassi.TrabalhoFaculLP1.e.A.infra;

import br.com.bassi.TrabalhoFaculLP1.e.A.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    // 🔐 Define a chave secreta usada para assinar o token
    private static final String SECRET_KEY = "chave-secreta-muito-forte"; // você pode alterar por algo mais seguro

    // ⏰ Define o tempo de expiração do token (1 hora aqui, em milissegundos)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    // ✅ Gera um token JWT para o usuário autenticado
    public String generateToken(Usuario user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // ✅ Extrai o nome do usuário a partir do token
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    // ✅ Valida se o token pertence ao usuário e se não está expirado
    public boolean validateToken(String token, org.springframework.security.core.userdetails.UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // ✅ Verifica se o token está expirado
    private boolean isTokenExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    // ✅ Extrai as Claims do token (dados codificados)
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
