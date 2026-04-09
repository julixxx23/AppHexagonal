package hexagonal.developer.detallepedido.infrastructure.adapter.out.persistence.mapper;

import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import hexagonal.developer.detallepedido.infrastructure.adapter.out.persistence.entity.DetallePedidoEntity;
import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.infrastructure.adapter.out.persistence.entity.PedidoEntity;
import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.producto.infrastructure.adapter.out.persistence.entity.ProductoEntity;
import org.springframework.stereotype.Component;

@Component
public class DetallePersistenceMapper {

    public DetallePedido toDomain(DetallePedidoEntity entity){
        Pedido pedido = Pedido.builder()
                .idPedido((entity.getPedido().getIdPedido()))
                .fechaPedido(entity.getPedido().getFechaPedido())
                .estadoPedido(entity.getPedido().getEstadoPedido())
                .activo(entity.getPedido().getActivo())
                .build();

        Producto producto = Producto.builder()
                .idProducto(entity.getProducto().getIdProducto())
                .nombre(entity.getProducto().getNombre())
                .descripcion(entity.getProducto().getDescripcion())
                .precio(entity.getProducto().getPrecio())
                .stock(entity.getProducto().getStock())
                .activo(entity.getProducto().getActivo())
                .fechaCreacion(entity.getProducto().getFechaCreacion())
                .build();

        return DetallePedido.builder()
                .idDetalle(entity.getIdDetalle())
                .cantidad(entity.getCantidad())
                .precioUnitario(entity.getPrecioUnitario())
                .subTotal(entity.getSubTotal())
                .pedido(pedido)
                .producto(producto)
                .build();
    }

    public DetallePedidoEntity toEntity(DetallePedido detallePedido){
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setIdPedido(detallePedido.getPedido().getIdPedido());

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setIdProducto(detallePedido.getProducto().getIdProducto());

        DetallePedidoEntity entity = new DetallePedidoEntity();
        entity.setIdDetalle(detallePedido.getIdDetalle());
        entity.setCantidad(detallePedido.getCantidad());
        entity.setPrecioUnitario(detallePedido.getPrecioUnitario());
        entity.setSubTotal(detallePedido.getSubTotal());
        entity.setPedido(pedidoEntity);
        entity.setProducto(productoEntity);
        return entity;
    }


}
