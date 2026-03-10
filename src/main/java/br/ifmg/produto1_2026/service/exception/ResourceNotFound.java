package br.ifmg.produto1_2026.service.exception;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String message) {
        super(message);
    }
    public ResourceNotFound() {}
}
