package hexagonal.developer.shared.fixture;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.shared.domain.model.PageDomain;

import java.time.LocalDateTime;
import java.util.List;

public class CategoriaFixture {

    public static Categoria unaCategoriaValida() {
        return Categoria.builder()
                .idCategoria(1L)
                .nombre("Analgésicos")
                .descripcion("Medicamentos para el dolor")
                .activo(true)
                .fechaCreacion(LocalDateTime.of(2024, 1, 15, 10, 0))
                .build();
    }

    public static Categoria unaCategoriaInactiva() {
        return Categoria.builder()
                .idCategoria(2L)
                .nombre("Descontinuados")
                .descripcion("Productos fuera de catálogo")
                .activo(false)
                .fechaCreacion(LocalDateTime.of(2024, 1, 15, 10, 0))
                .build();
    }
    public static Categoria unaCategoriaSinId(){
        return Categoria.builder()
                .nombre("Niños")
                .descripcion("De 0 a 4 años")
                .activo(true)
                .fechaCreacion(LocalDateTime.of(2025, 1, 14, 16, 7))
                .build();
    }

    public static List<Categoria> unaListDeCategoria(){
        return List.of(unaCategoriaValida(), Categoria.builder()
                        .idCategoria(1L)
                        .nombre("Vitaminas")
                        .descripcion("Reactiva el cuerpo")
                        .activo(true)
                        .fechaCreacion(LocalDateTime.of(2025, 1, 14, 10, 0))
                        .build()
        );


    }
    public static PageDomain<Categoria> unPageDomaninDeCategorias(){
        return new PageDomain<>(unaListDeCategoria(), 0, 1, 2L);
    }
}