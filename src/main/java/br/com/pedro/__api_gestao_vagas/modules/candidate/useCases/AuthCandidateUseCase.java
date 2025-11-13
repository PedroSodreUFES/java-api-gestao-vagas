package br.com.pedro.__api_gestao_vagas.modules.candidate.useCases;

import br.com.pedro.__api_gestao_vagas.modules.candidate.CandidateEntity;
import br.com.pedro.__api_gestao_vagas.modules.candidate.controller.CandidateRepository;
import br.com.pedro.__api_gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.pedro.__api_gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}") // pega do application.properties
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        // Vê se o candidato existe
        CandidateEntity candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Invalid credentials.");
        });

        // Vê se as senhas batem
        Boolean senhasBatem = passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

        // se não baterem
        if(senhasBatem.equals(false)) {
            throw new AuthenticationException("Invalid credentials.");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        String token = JWT.create()
                .withIssuer("Pedro Sodré LTDA") // coloca meu nome como issuer
                .withSubject(candidate.getId().toString()) // assina com o id do usuário
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withExpiresAt(expiresIn) // dura 2 horas
                .sign(algorithm); // assina o token

        return AuthCandidateResponseDTO.builder()
                .accessToken(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();
    }
}
