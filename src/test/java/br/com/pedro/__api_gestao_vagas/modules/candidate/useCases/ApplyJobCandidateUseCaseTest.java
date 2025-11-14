package br.com.pedro.__api_gestao_vagas.modules.candidate.useCases;

import br.com.pedro.__api_gestao_vagas.exceptions.JobNotFound;
import br.com.pedro.__api_gestao_vagas.exceptions.UserNotFound;
import br.com.pedro.__api_gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.pedro.__api_gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.pedro.__api_gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.pedro.__api_gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.pedro.__api_gestao_vagas.modules.company.entities.JobEntity;
import br.com.pedro.__api_gestao_vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks // classe a ser testada
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock // dependencia da classe a ser testada
    private CandidateRepository candidateRepository;

    @Mock  // dependencia da classe a ser testada
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with a candidate that does not exist.")
    public void candidateNotFound(){
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (UserNotFound e) {
            assertThat(e).isInstanceOf(UserNotFound.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply to a job that does not exist.")
    public void jobNotFound(){
        UUID candidateId = UUID.randomUUID();

        CandidateEntity candidate = new CandidateEntity();
        candidate.setId(candidateId);

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));
        try {
            applyJobCandidateUseCase.execute(candidateId, null);
        } catch (JobNotFound ex){
            assertThat(ex).isInstanceOf(JobNotFound.class);
        }
    }

    @Test
    @DisplayName("Should be able to apply to a job.")
    public void success(){
        UUID candidateId = UUID.randomUUID();
        UUID jobId = UUID.randomUUID();

        ApplyJobEntity applyJob = ApplyJobEntity.builder()
                .jobId(jobId)
                .candidateId(candidateId)
                .build();

        ApplyJobEntity applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));
        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(candidateId, jobId);

        assertThat(result).hasFieldOrProperty("id");
        assertThat(result.getId()).isNotEqualTo(null);
    }
}
