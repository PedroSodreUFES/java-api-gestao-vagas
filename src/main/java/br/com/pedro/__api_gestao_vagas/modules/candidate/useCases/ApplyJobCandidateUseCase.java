package br.com.pedro.__api_gestao_vagas.modules.candidate.useCases;

import br.com.pedro.__api_gestao_vagas.exceptions.JobNotFound;
import br.com.pedro.__api_gestao_vagas.exceptions.UserNotFound;
import br.com.pedro.__api_gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.pedro.__api_gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.pedro.__api_gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.pedro.__api_gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID candidateId, UUID jobId) {
        this.candidateRepository.findById(candidateId).orElseThrow(() -> new UserNotFound());
        this.jobRepository.findById(jobId).orElseThrow(() -> new JobNotFound());

        ApplyJobEntity applyJobEntity = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId).build();

        return this.applyJobRepository.save(applyJobEntity);
    }
}
