package br.com.pedro.__api_gestao_vagas.modules.candidate.useCases;

import br.com.pedro.__api_gestao_vagas.exceptions.UserNotFound;
import br.com.pedro.__api_gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.pedro.__api_gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId){
        var candidate = this.candidateRepository
                .findById(candidateId).orElseThrow(() -> new UserNotFound());

        return ProfileCandidateResponseDTO.builder()
                .username(candidate.getUsername())
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .id(candidateId)
                .name(candidate.getName())
                .build();
    }
}
