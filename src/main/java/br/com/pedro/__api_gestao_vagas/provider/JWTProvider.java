package br.com.pedro.__api_gestao_vagas.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {
    @Value("${security.token.secret}") // pega do application.properties
    private String secretKey;

    public String validateToken(String token) {
        try {
            token = token.replace("Bearer ", ""); // tira o Bearer
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm).build().verify(token).getSubject();
        } catch (JWTVerificationException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
