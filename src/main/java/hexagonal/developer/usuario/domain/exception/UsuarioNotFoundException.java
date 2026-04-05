package hexagonal.developer.usuario.domain.exception;

import hexagonal.developer.shared.domain.exception.NotFoundException;

public class UsuarioNotFoundException extends NotFoundException {
    public UsuarioNotFoundException(Long id){
        super("Usuario no encontrado por id: " + id);
    }
}
