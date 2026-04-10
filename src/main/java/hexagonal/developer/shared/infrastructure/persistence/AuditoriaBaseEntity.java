package hexagonal.developer.shared.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditoriaBaseEntity {

    @CreatedDate
    @Column(name = "auditoria_fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime auditoriaFechaCreacion;

    public LocalDateTime getAuditoriaFechaCreacion() {
        return auditoriaFechaCreacion;
    }
}