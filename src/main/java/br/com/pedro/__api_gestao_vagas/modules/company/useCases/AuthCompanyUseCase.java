package br.com.pedro.__api_gestao_vagas.modules.company.useCases;

import br.com.pedro.__api_gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.pedro.__api_gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.pedro.__api_gestao_vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.swing.*;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {
    @Value("${security.token.secret}") // pega do application.properties
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        // ve se a company existe
        CompanyEntity company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> {
            throw new UsernameNotFoundException("Company not found.");
        });

        // Verificar se as senhas batem
        Boolean senhasSaoIguais = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        // Se não batem, joga o erro.
        if(senhasSaoIguais.equals(false)){
            throw new AuthenticationException("Credentials do not match.");
        }

        // Se forem iguais, fazer o token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("Pedro Sodré LTDA") // Coloca sodrézin vapo vapo como issuer
                .withExpiresAt(Instant.now().plus(Duration.ofHours(1))) // expira em 1 hora e coloca o campo exp no token
                .withSubject(company.getId().toString()) // coloca o sub com o id da company
                .sign(algorithm); // algoritmo HMAC256

        return token;
    }
}
