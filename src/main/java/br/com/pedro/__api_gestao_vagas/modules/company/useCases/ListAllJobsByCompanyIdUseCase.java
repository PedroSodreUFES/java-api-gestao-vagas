package br.com.pedro.__api_gestao_vagas.modules.company.useCases;

import br.com.pedro.__api_gestao_vagas.modules.company.entities.JobEntity;
import br.com.pedro.__api_gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListAllJobsByCompanyIdUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(UUID companyId) {
        return this.jobRepository.findByCompanyId(companyId);
    }
}
