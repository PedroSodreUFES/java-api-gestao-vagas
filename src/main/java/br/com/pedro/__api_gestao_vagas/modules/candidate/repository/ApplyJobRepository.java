package br.com.pedro.__api_gestao_vagas.modules.candidate.repository;

import br.com.pedro.__api_gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}
