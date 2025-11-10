package br.com.pedro.__api_gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // cria um construtor com password e username
public class AuthCompanyDTO {

    private String password;
    private String username;
}
