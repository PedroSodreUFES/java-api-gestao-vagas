package br.com.pedro.__api_gestao_vagas.modules.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Pattern(regexp = "[a-zA-Z0-9_-]{5,20}", message = "O campo username deve conter os caracteres a-zA-Z0-9_-, sendo no mínimo 5 e no máximo 20 caracteres.")
    private String username;

    private String name;

    @Email(message = "O campo email deve conter um e-mail válido.")
    private String email;

    @Length(min=6, max=100, message = "A senha deve ter no mínimo 6 e no máximo 100 caracteres")
    private String password;
    private String website;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
