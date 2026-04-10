package hexagonal.developer.detallepedido.infrastructure.adapter.in.rest.mapper;

import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import hexagonal.developer.detallepedido.infrastructure.adapter.in.rest.dto.DetalleCreateRequest;
import hexagonal.developer.detallepedido.infrastructure.adapter.in.rest.dto.DetalleResponse;
import hexagonal.developer.detallepedido.infrastructure.adapter.in.rest.dto.DetalleUpdateRequest;
import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.infrastructure.adapter.in.rest.dto.PedidoSimpleResponse;
import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.producto.infrastructure.adapter.in.rest.dto.ProductoSimpleResponse;
import org.springframework.stereotype.Component;

@Component
public class DetalleRestMapper {

    public DetallePedido toDomain(DetalleCreateRequest request, Long idPedido){
        return DetallePedido.builder()
                .cantidad(request.getCantidad())
                .producto(Producto.builder()
                        .idProducto(request.getIdProducto())
                        .build())
                .pedido(Pedido.builder()
                        .idPedido(idPedido)
                        .build())
                .build();
    }
    public DetallePedido toDomain(DetalleUpdateRequest request){
        return DetallePedido.builder()
                .cantidad(request.getCantidad())
                .build();
    }

    public DetalleResponse toResponse(DetallePedido detallePedido){
        return DetalleResponse.builder()
                .idDetalle(detallePedido.getIdDetalle())
                .cantidad(detallePedido.getCantidad())
                .precioUnitario(detallePedido.getPrecioUnitario())
                .subTotal(detallePedido.getSubTotal())
                .producto(ProductoSimpleResponse.builder()
                        .idProducto(detallePedido.getProducto().getIdProducto())
                        .nombre(detallePedido.getProducto().getNombre())
                        .build())
                .pedido(PedidoSimpleResponse.builder()
                        .idPedido(detallePedido.getPedido().getIdPedido())
                        .build())
                .build();


    }
}
