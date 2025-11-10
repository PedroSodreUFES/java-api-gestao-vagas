package br.com.pedro.__api_gestao_vagas.modules.candidate.controller;

import br.com.pedro.__api_gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.pedro.__api_gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.pedro.__api_gestao_vagas.modules.candidate.useCases.AuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/candidate")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping()
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO request) {
        try {
            AuthCandidateResponseDTO token = authCandidateUseCase.execute(request);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
