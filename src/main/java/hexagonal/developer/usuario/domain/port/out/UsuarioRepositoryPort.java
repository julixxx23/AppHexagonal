package hexagonal.developer.usuario.domain.port.out;

import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.usuario.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuario guardar(Usuario usuario);
    Usuario actualizar(Long id, Usuario usuario);
    PageDomain<Usuario> listarTodos(int pagina, int tamanio);
    PageDomain<Usuario> buscarPorTexto(String texto, int pagina, int tamanio);
    Optional<Usuario> buscarPorId(Long id);
    void eliminar(Long id);
    Usuario estado(Long id, Boolean estado);
    boolean existePorUsuarioUsuario(String usuarioUsuario);
    boolean existePorUsuarioUsuarioExcluyendoId(String usuarioUsuario, Long id);
}