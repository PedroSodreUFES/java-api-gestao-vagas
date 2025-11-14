package br.com.pedro.__api_gestao_vagas.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("Usuário não encontrado.");
    }
}
