package br.com.pedro.__api_gestao_vagas.exceptions;

public class CompanyFoundException extends RuntimeException {
    public CompanyFoundException() {
        super("Empresa já encontrada!");
    }
}
