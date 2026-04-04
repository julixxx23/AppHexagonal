package hexagonal.developer.usuario.aplication;

import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.in.ListarTodosUsuariosPort;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListarTodosUsuariosUseCase implements ListarTodosUsuariosPort {
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public PageDomain<Usuario> listarTodos(int pagina, int tamanio){
        return usuarioRepositoryPort.buscarPorTexto(pagina, tamanio);
    }
}
