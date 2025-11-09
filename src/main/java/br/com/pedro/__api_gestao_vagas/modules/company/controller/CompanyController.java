package br.com.pedro.__api_gestao_vagas.modules.company.controller;

import br.com.pedro.__api_gestao_vagas.exceptions.CompanyFoundException;
import br.com.pedro.__api_gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.pedro.__api_gestao_vagas.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity){
        try {
            CompanyEntity company =  this.createCompanyUseCase.execute(companyEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(company);
        } catch (CompanyFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
