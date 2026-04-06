package hexagonal.developer.usuario.infrastructure.adapter.out.persistence.entity;

import hexagonal.developer.shared.infrastructure.persistence.AuditoriaBaseEntity;
import hexagonal.developer.usuario.domain.model.RolesUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class UsuarioEntity extends AuditoriaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "usuario_usuario", nullable = false, length = 20)
    private String usuarioUsuario;

    @Column(name = "nombre_usuario", nullable = false, length = 150)
    private String nombreUsuario;

    @Column(name = "apellido_usuario", nullable = false, length = 150)
    private String apellidoUsuario;

    @Column(name = "contrasena_hash", nullable = false)
    private String contrasenaHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol_usuario", nullable = false, length = 10)
    private RolesUsuario rolUsuario;

    @Column(name = "usuario_estado", nullable = false)
    private Boolean usuarioEstado;

    @Column(name = "activo")
    private Boolean activo = true;
}