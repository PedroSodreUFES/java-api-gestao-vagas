package br.com.pedro.__api_gestao_vagas.modules.candidate.controller;

import br.com.pedro.__api_gestao_vagas.modules.candidate.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    // spring faz o méthodo automaticamente só pelo nome dele. Caso ache o usuario retorna um candidato. Senão, retorna nulo
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
}
