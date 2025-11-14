package br.com.pedro.__api_gestao_vagas.modules.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

public class Utils {
    public static String objectToJson(Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID companyId, String secretKey) {
        Instant expiresIn = Instant.now().plus(Duration.ofHours(2));
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create().withIssuer("Pedro Sodré LTDA") // Coloca sodrézin vapo vapo como issuer
                .withExpiresAt(expiresIn) // expira em 1 hora e coloca o campo exp no token
                .withSubject(companyId.toString()) // coloca o sub com o id da company
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm); // algoritmo HMAC256
    }
}
