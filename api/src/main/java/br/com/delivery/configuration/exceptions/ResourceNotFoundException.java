package br.com.delivery.configuration.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " não encontrado(a) com ID " + id);
    }
}
