package hexagonal.developer.pedido.infrastructure.adapter.in.rest;

import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.dominio.port.in.*;
import hexagonal.developer.pedido.infrastructure.adapter.in.rest.dto.PedidoCreateRequest;
import hexagonal.developer.pedido.infrastructure.adapter.in.rest.dto.PedidoResponse;
import hexagonal.developer.pedido.infrastructure.adapter.in.rest.mapper.PedidoMapperRequest;
import hexagonal.developer.shared.domain.model.PageDomain;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController{

    private final GuardarPedidoPort guardarPedidoPort;
    private final ActualizarPedidoPort actualizarPedidoPort;
    private final BuscarPedidoPorIdPort pedidoPorIdPort;
    private final BuscarPedidoPorTextoPort pedidoPorTextoPort;
    private final ListarPedidoPort listarPedidoPort;
    private final CancelarPedidoPort cancelarPedidoPort;
    private final AvanzarEstadoPedidoPort avanzarEstadoPedidoPort;
    private final PedidoMapperRequest mapper;

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable Long id){
        Pedido pedido = pedidoPorIdPort.buscarPorId(id);
        return ResponseEntity.ok(mapper.toResponse(pedido));
    }

    @GetMapping("/buscar")
    public ResponseEntity<PageDomain<PedidoResponse>> buscarPorTexto(
            @RequestParam String texto,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio){
        PageDomain<Pedido> page = pedidoPorTextoPort.buscarPorTexto(texto, pagina, tamanio);
        PageDomain<PedidoResponse> pageDomain = new PageDomain<>(
                page.contenido().stream().map(mapper::toResponse).toList(),
                page.paginaActual(),
                page.totalPaginas(),
                page.totalElementos()
        );
        return ResponseEntity.ok(pageDomain);
    }

    @GetMapping
    public ResponseEntity<PageDomain<PedidoResponse>> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio){
        PageDomain<Pedido> page = listarPedidoPort.listar(pagina, tamanio);
        PageDomain<PedidoResponse> pageDomain = new PageDomain<>(
                page.contenido().stream().map(mapper::toResponse).toList(),
                page.paginaActual(),
                page.totalPaginas(),
                page.totalElementos()
        );
        return ResponseEntity.ok(pageDomain);
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> guardar(@Valid @RequestBody PedidoCreateRequest request){
        Pedido pedido = guardarPedidoPort.guardar(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(pedido));
    }

    @PatchMapping("/{id}/avanzar")
    public ResponseEntity<PedidoResponse> avanzarEstado(@PathVariable Long id){
        Pedido pedido = avanzarEstadoPedidoPort.avanzarEstado(id);
        return ResponseEntity.ok(mapper.toResponse(pedido));

    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<PedidoResponse> cancelar(@Valid @PathVariable Long id){
        Pedido pedido = cancelarPedidoPort.cancelar(id);
        return ResponseEntity.ok(mapper.toResponse(pedido));
    }


}
