package hexagonal.developer.producto.infrastructure.adapter.in.rest;

import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.producto.domain.port.in.*;
import hexagonal.developer.producto.infrastructure.adapter.in.rest.dto.ProductoCreateRequest;
import hexagonal.developer.producto.infrastructure.adapter.in.rest.dto.ProductoResponse;
import hexagonal.developer.producto.infrastructure.adapter.in.rest.dto.ProductoUpdateRequest;
import hexagonal.developer.producto.infrastructure.adapter.in.rest.mapper.ProductoRestMapper;
import hexagonal.developer.shared.domain.model.PageDomain;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final GuardarProductoPort guardarProductoPort;
    private final BuscarProductoPort buscarProductoPort;
    private final ListarProductosPort listarProductosPort;
    private final BuscarPorTextoProductoPort buscarPorTextoProductoPort;
    private final ActualizarProductoPort actualizarProductoPort;
    private  final EliminarProductoPort eliminarProductoPort;
    private final ProductoRestMapper mapper;



    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> buscarPorId(@PathVariable Long id){
        Producto producto = buscarProductoPort.buscarPorId(id);
        return ResponseEntity.ok(mapper.toResponse(producto));
    }

    @GetMapping
    public ResponseEntity<PageDomain<ProductoResponse>> listarTodas(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio){
        PageDomain<Producto> page = listarProductosPort.listarTodas(pagina,tamanio);
        PageDomain<ProductoResponse> response = new PageDomain<>(
                page.contenido().stream().map(mapper::toResponse).toList(),
                page.paginaActual(),
                page.totalPaginas(),
                page.totalElementos()
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/buscar")
    public ResponseEntity<PageDomain<ProductoResponse>> buscarPorTexto(
            @RequestParam String texto,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio){
        PageDomain<Producto> page = buscarPorTextoProductoPort.buscarPorTexto(texto, pagina, tamanio);
        PageDomain<ProductoResponse> responsePage = new PageDomain<>(
                page.contenido().stream().map(mapper::toResponse).toList(),
                page.paginaActual(),
                page.totalPaginas(),
                page.totalElementos()
        );
        return ResponseEntity.ok(responsePage);
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> guardar(@Valid @RequestBody ProductoCreateRequest request){
        Producto producto = guardarProductoPort.guardar(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoUpdateRequest request){
        Producto producto = actualizarProductoPort.actualizar(id, mapper.toDomain(request));
        return ResponseEntity.ok(mapper.toResponse(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        eliminarProductoPort.eliminar(id);
        return ResponseEntity.noContent().build();
    }




}