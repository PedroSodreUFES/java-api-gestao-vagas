package br.com.pedro.__api_gestao_vagas.modules.candidate;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data // Lombok faz validação de getters e setters automaticamente
public class CandidateEntity {

    private UUID id;
    private String name;

    @Pattern(regexp = "[a-zA-Z0-9_-]{5,20}", message = "O campo username pode conter somente os caracteres a-z A-Z 0-9 _ -, tendo no mínimo 5 caaracteres e no máximo 20 caracteres")
    private String username;

    // spring validation para validar campos -> no caso esse é de email e se não vir um email dá um erro
    @Email(message = "O campo 'email' deve conter um e-mail válido.")
    private String email;

    @Length(min=4, max=100)
    private String password;
    private String description;
    private String curriculum;

}
