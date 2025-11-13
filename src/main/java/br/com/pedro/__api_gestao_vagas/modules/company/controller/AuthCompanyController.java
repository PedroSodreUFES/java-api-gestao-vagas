package br.com.pedro.__api_gestao_vagas.modules.company.controller;

import br.com.pedro.__api_gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.pedro.__api_gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.pedro.__api_gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/company/auth")
public class AuthCompanyController {
    @Autowired
    AuthCompanyUseCase authCompanyUseCase;

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            AuthCompanyResponseDTO response = this.authCompanyUseCase.execute(authCompanyDTO);
            return ResponseEntity.ok().body(response);
        } catch (AuthenticationException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        } catch (UsernameNotFoundException ex2) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex2.getMessage());
        }
    }
}
