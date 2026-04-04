package hexagonal.developer.usuario.aplication;

import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.in.BuscarUsuarioPorTextoPort;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarUsuarioPorTextoUseCase implements BuscarUsuarioPorTextoPort {
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public PageDomain<Usuario> buscarPorTexto(String texto, int pagina, int tamanio){
        return usuarioRepositoryPort.buscarPorTexto(texto, pagina, tamanio);
    }
}
