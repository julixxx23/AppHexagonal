package hexagonal.developer.shared.fixture;

import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.shared.domain.model.PageDomain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProductoFixture {

    public static Producto unProductoValido() {
        return Producto.builder()
                .idProducto(1L)
                .nombre("Paracetamol 500mg")
                .descripcion("Analgésico de uso común")
                .precio(new BigDecimal("25.50"))
                .stock(100)
                .activo(true)
                .fechaCreacion(LocalDateTime.of(2024, 1, 15, 10, 0))
                .categoria(CategoriaFixture.unaCategoriaValida())
                .build();
    }

    public static Producto unProductoSinId() {
        return Producto.builder()
                .nombre("Amoxicilina 250mg")
                .descripcion("Antibiótico de amplio espectro")
                .precio(new BigDecimal("45.00"))
                .stock(50)
                .activo(true)
                .fechaCreacion(LocalDateTime.of(2024, 1, 15, 10, 0))
                .categoria(CategoriaFixture.unaCategoriaValida())
                .build();
    }

    public static List<Producto> unaListaDeProductos() {
        return List.of(
                unProductoValido(),
                Producto.builder()
                        .idProducto(2L)
                        .nombre("Ibuprofeno 400mg")
                        .descripcion("Antiinflamatorio")
                        .precio(new BigDecimal("30.00"))
                        .stock(75)
                        .activo(true)
                        .fechaCreacion(LocalDateTime.of(2024, 1, 15, 10, 0))
                        .categoria(CategoriaFixture.unaCategoriaValida())
                        .build()
        );
    }

    public static PageDomain<Producto> unPageDomainDeProductos() {
        return new PageDomain<>(unaListaDeProductos(), 0, 1, 2L);
    }
}