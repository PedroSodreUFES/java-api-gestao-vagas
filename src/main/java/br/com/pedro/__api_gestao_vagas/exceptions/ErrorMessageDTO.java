package br.com.pedro.__api_gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //ciras os getters e setters
@AllArgsConstructor // cria construtor com os parametros
public class ErrorMessageDTO {
    private String message;
    private String field;
}
