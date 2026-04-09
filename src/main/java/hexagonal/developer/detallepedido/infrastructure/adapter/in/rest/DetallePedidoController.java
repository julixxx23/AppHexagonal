package hexagonal.developer.detallepedido.infrastructure.adapter.in.rest;

import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import hexagonal.developer.detallepedido.domain.port.in.*;
import hexagonal.developer.detallepedido.infrastructure.adapter.in.rest.dto.DetalleCreateRequest;
import hexagonal.developer.detallepedido.infrastructure.adapter.in.rest.dto.DetalleResponse;
import hexagonal.developer.detallepedido.infrastructure.adapter.in.rest.dto.DetalleUpdateRequest;
import hexagonal.developer.detallepedido.infrastructure.adapter.in.rest.mapper.DetalleRestMapper;
import hexagonal.developer.shared.domain.model.PageDomain;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detalles")
@RequiredArgsConstructor
public class DetallePedidoController {
    private final GuardarDetallePort guardarDetallePort;
    private final ActualizarDetallePort actualizarDetallePort;
    private final BuscarDetallePorIdPort buscarDetallePorIdPort;
    private final ListarDetallesPort listarDetallesPort;
    private final EliminarDetallePort eliminarDetallePort;
    private final DetalleRestMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<DetalleResponse> buscarPorId(@PathVariable Long id){
        DetallePedido detallePedido = buscarDetallePorIdPort.buscarPorId(id);
        return ResponseEntity.ok(mapper.toResponse(detallePedido));
    }

    @GetMapping("/listar")
    public ResponseEntity<PageDomain<DetalleResponse>> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio){
        PageDomain<DetallePedido> page = listarDetallesPort.listar(pagina, tamanio);
        PageDomain<DetalleResponse> pageDomain = new PageDomain<>(
                page.contenido().stream().map(mapper::toResponse).toList().stream().toList(),
                page.paginaActual(),
                page.totalPaginas(),
                page.totalElementos()
        );
        return ResponseEntity.ok(pageDomain);
    }

    @PostMapping("/{idPedido}/detalles")
    public ResponseEntity<DetalleResponse> guardar(@PathVariable Long idPedido, @Valid @RequestBody DetalleCreateRequest request){
        DetallePedido detallePedido = guardarDetallePort.guardar(mapper.toDomain(request, idPedido));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(detallePedido));
    }
    @PutMapping("/{idDetalle}")
    public ResponseEntity<DetalleResponse> actualizar(
            @PathVariable Long idDetalle,
            @Valid @RequestBody DetalleUpdateRequest request){

        DetallePedido detalle = actualizarDetallePort.actualizar(idDetalle, mapper.toDomain(request));
        return ResponseEntity.ok(mapper.toResponse(detalle));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        eliminarDetallePort.eliminar(id);
        return ResponseEntity.noContent().build();
    }



    }



