package br.com.pedro.__api_gestao_vagas.modules.company.controller;

import br.com.pedro.__api_gestao_vagas.exceptions.CompanyNotFound;
import br.com.pedro.__api_gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.pedro.__api_gestao_vagas.modules.company.entities.JobEntity;
import br.com.pedro.__api_gestao_vagas.modules.company.useCases.CreateJobUseCase;
import br.com.pedro.__api_gestao_vagas.modules.company.useCases.ListAllJobsByCompanyIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/company/job")
@Tag(name = "Vagas", description = "Informações das vagas.")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @Autowired
    private ListAllJobsByCompanyIdUseCase listAllJobsByCompanyIdUseCase;

    @PostMapping()
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vagas", description = "Informações das vagas")
    @Operation(summary = "Cadastro de vagas.", description = "Esse endpoint é responsável por cadastrar as vagas de uma empresa.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = JobEntity.class))})
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request){
        // pega do header o company id e usa
        var companyId = request.getAttribute("company_id");

        try {
            // Transforma o DTO em job Entity
            JobEntity jobEntity = JobEntity.builder()
                    .benefits(createJobDTO.getBenefits())
                    .companyId(UUID.fromString(companyId.toString()))
                    .description(createJobDTO.getDescription())
                    .level(createJobDTO.getLevel())
                    .build();

            // Executa
            JobEntity job = createJobUseCase.execute(jobEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(job);
        } catch (CompanyNotFound ex) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping()
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vagas", description = "Informações das vagas")
    @Operation(summary = "Recupera vagas de uma empresa.", description = "Esse endpoint é responsável por listar as vagas de uma empresa.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))})
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<List<JobEntity>> listByCompanyId(HttpServletRequest request){
        String companyId = request.getAttribute("company_id").toString();
        List<JobEntity> jobs = this.listAllJobsByCompanyIdUseCase.execute(UUID.fromString(companyId));
        return ResponseEntity.ok(jobs);
    }
}

