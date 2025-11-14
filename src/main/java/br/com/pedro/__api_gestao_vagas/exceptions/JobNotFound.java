package br.com.pedro.__api_gestao_vagas.exceptions;

public class JobNotFound extends RuntimeException {
    public JobNotFound() {
        super("Job not found");
    }
}
