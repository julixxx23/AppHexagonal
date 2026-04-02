package hexagonal.developer.categoria.infrastructure.adapter.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaSimpleResponse {

    private Long id;
    private String nombre;


}
