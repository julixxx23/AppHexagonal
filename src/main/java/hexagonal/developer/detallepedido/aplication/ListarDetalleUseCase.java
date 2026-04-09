package hexagonal.developer.detallepedido.aplication;

import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import hexagonal.developer.detallepedido.domain.port.in.ListarDetallesPort;
import hexagonal.developer.detallepedido.domain.port.out.DetalleRepositoryPort;
import hexagonal.developer.shared.domain.model.PageDomain;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListarDetalleUseCase implements ListarDetallesPort {
    private final DetalleRepositoryPort detalleRepositoryPort;

    @Override
    public PageDomain<DetallePedido> listar(int pagina, int tamanio){
        return detalleRepositoryPort.listar(pagina, tamanio);
    }
}
