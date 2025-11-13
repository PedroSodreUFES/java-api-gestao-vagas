package br.com.pedro.__api_gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {
    @Schema(example = "Desenvolvedor pleno full-stack em Java.")
    private String description;
    @Schema(example = "pedro_sodrem")
    private String username;
    @Schema(example = "Pedro Sodré")
    private String name;
    private UUID id;
    @Schema(example = "pedro.malini@ctjunior.com.br")
    private String email;
}
