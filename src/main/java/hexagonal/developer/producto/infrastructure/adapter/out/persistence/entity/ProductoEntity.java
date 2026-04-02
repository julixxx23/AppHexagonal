package hexagonal.developer.producto.infrastructure.adapter.out.persistence.entity;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.infrastructure.adapter.out.persistence.entity.CategoriaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "productos")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "descripcion", length = 500 )
    private String descripcion;

    @Column(name = "precio")
    private BigDecimal precio;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntity categoria;


}
