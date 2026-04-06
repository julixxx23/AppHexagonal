package hexagonal.developer.usuario.infrastructure.adapter.in.rest;

import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.in.*;
import hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto.UsuarioCreateRequest;
import hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto.UsuarioResponse;
import hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto.UsuarioUpdateRequest;
import hexagonal.developer.usuario.infrastructure.adapter.in.rest.mapper.UsuarioRestMapper;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final GuardarUsuarioPort guardarUsuarioPort;
    private final ActualizarUsuarioPort actualizarUsuarioPort;
    private final BuscarUsuarioPorIdPort buscarUsuarioPorIdPort;
    private final BuscarUsuarioPorTextoPort buscarUsuarioPorTextoPort;
    private final ListarTodosUsuariosPort listarTodosUsuariosPort;
    private final EliminarIUsuarioPort eliminarIUsuarioPort;
    private final EstadoUsuarioPort estadoUsuarioPort;
    private final UsuarioRestMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        Usuario usuario = buscarUsuarioPorIdPort.buscarPorId(id);
        return ResponseEntity.ok(mapper.toResponse(usuario));
    }

    @GetMapping("/buscar")
    public ResponseEntity<PageDomain<UsuarioResponse>> buscarPorTexto(
            @RequestParam String texto,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio){
        PageDomain<Usuario> page  = buscarUsuarioPorTextoPort.buscarPorTexto(texto, pagina, tamanio);
        PageDomain<UsuarioResponse> responsePageDomain = new PageDomain<>(
                page.contenido().stream().map(mapper::toResponse).toList(),
                page.paginaActual(),
                page.totalPaginas(),
                page.totalElementos()
        );
        return ResponseEntity.ok(responsePageDomain);
    }

    @GetMapping
    public ResponseEntity<PageDomain<UsuarioResponse>> listarTodas(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio){
        PageDomain<Usuario> page = listarTodosUsuariosPort.listarTodos(pagina, tamanio);
        PageDomain<UsuarioResponse> pageDomain = new PageDomain<>(
                page.contenido().stream().map(mapper::toResponse).toList(),
                page.paginaActual(),
                page.totalPaginas(),
                page.totalElementos()
        );
        return ResponseEntity.ok(pageDomain);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> guardar(@Valid @RequestBody UsuarioCreateRequest request){
        Usuario usuario = guardarUsuarioPort.guardar(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizar(@PathVariable long id, @Valid @RequestBody UsuarioUpdateRequest request){
        Usuario usuario = actualizarUsuarioPort.actualizar(id, mapper.toDomain(request));
        return ResponseEntity.ok(mapper.toResponse(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        eliminarIUsuarioPort.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Usuario> estado(@PathVariable Long id, @RequestParam Boolean estado) {
        return ResponseEntity.ok(estadoUsuarioPort.estado(id, estado));
    }

}
