package br.com.pedro.__api_gestao_vagas.modules.candidate.useCases;

import br.com.pedro.__api_gestao_vagas.exceptions.UserFoundException;
import br.com.pedro.__api_gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.pedro.__api_gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity){
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        String passwordHashed = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(passwordHashed);

        return this.candidateRepository.save(candidateEntity);
    }
}
