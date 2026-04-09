package hexagonal.developer.detallepedido.aplication;

import hexagonal.developer.detallepedido.domain.exception.DetalleYaExisteException;
import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import hexagonal.developer.detallepedido.domain.port.in.GuardarDetallePort;
import hexagonal.developer.detallepedido.domain.port.out.DetalleRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GuardarDetalleUseCase implements GuardarDetallePort {
    private final DetalleRepositoryPort detalleRepositoryPort;

    @Override
    public DetallePedido guardar(DetallePedido detallePedido){
        detallePedido.getPedido().validarEditable();

        if(detalleRepositoryPort.existePorPedidoYProducto(
                detallePedido.getPedido().getIdPedido(),
                detallePedido.getProducto().getIdProducto())){
            throw  new DetalleYaExisteException("El producto ya esta en el pedido");
        }
        return detalleRepositoryPort.guardar(detallePedido);
    }

}