package hexagonal.developer.categoria.infrastructure.adapter.in.rest;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.categoria.domain.port.in.BuscarCategoriaPort;
import hexagonal.developer.categoria.domain.port.in.GuardarCategoriaPort;
import hexagonal.developer.categoria.domain.port.in.ListarCategoriasPort;
import hexagonal.developer.categoria.infrastructure.adapter.in.rest.dto.CategoriaCreateRequest;
import hexagonal.developer.categoria.infrastructure.adapter.in.rest.dto.CategoriaResponse;
import hexagonal.developer.categoria.infrastructure.adapter.in.rest.mapper.CategoriaRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final GuardarCategoriaPort guardarCategoriaPort;
    private final BuscarCategoriaPort buscarCategoriaPort;
    private final ListarCategoriasPort listarCategoriasPort;
    private final CategoriaRestMapper mapper;

    @PostMapping
    public ResponseEntity<CategoriaResponse> guardar(@Valid @RequestBody CategoriaCreateRequest request) {
        Categoria categoria = guardarCategoriaPort.guardar(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable Long id) {
        Categoria categoria = buscarCategoriaPort.buscarPorId(id);
        return ResponseEntity.ok(mapper.toResponse(categoria));
    }

    @GetMapping
    public ResponseEntity<PageDomain<CategoriaResponse>> listarTodas(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio) {
        PageDomain<Categoria> page = listarCategoriasPort.listarTodas(pagina, tamanio);
        PageDomain<CategoriaResponse> response = new PageDomain<>(
                page.contenido().stream().map(mapper::toResponse).toList(),
                page.paginaActual(),
                page.totalPaginas(),
                page.totalElementos()
        );
        return ResponseEntity.ok(response);
    }
}