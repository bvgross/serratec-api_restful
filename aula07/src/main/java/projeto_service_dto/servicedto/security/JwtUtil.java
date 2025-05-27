package projeto_service_dto.servicedto.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${auth.jwt-secret}")
	private String jwtSecret; //chave usado para gerar criptografia do token
	
	@Value("${auth.jwt-expiration-miliseg}")
	private Long jwtExpirationMiliseg; //tempo que o token dura
	
	public String generateToken(String username) { //gera o token
		SecretKey secreKeySpec = Keys.hmacShaKeyFor(jwtSecret.getBytes());
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + 
						this.jwtExpirationMiliseg))
				.signWith(secreKeySpec)
				.compact();
	}

	public Claims getClaims(String token) { //decodifica????
		return Jwts.parserBuilder()
				.setSigningKey(jwtSecret.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public boolean isValidToken(String token) { //verifica se o token é valido
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}
	
	
	public String getUserName(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
	
}
