package br.com.pedro.__api_gestao_vagas.modules.candidate.controller;

import br.com.pedro.__api_gestao_vagas.modules.candidate.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> { }
