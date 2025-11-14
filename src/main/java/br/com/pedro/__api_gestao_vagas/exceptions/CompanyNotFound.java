package br.com.pedro.__api_gestao_vagas.exceptions;

public class CompanyNotFound extends RuntimeException {
    public CompanyNotFound() {
        super("Company not found.");
    }
}
