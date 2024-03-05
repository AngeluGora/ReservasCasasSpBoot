package es.angelu.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data// Lombok Genera automáticamente getters, setters, toString(), equals() y hashCode() para todos los campos de la clase.
@NoArgsConstructor //Lombok Genera un constructor sin argumentos.
@AllArgsConstructor  //Lombok Genera un constructor que acepta todos los campos
@Builder
@Entity
@Table(name = "USUARIOS")
public class Usuario {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "miSecuencia2")
    @SequenceGenerator(name = "miSecuencia2", sequenceName = "DAM2_SEQ_USUARIO", allocationSize = 1)
    @Id
    private long idU;

    private String nombreUsuario;
    private String password;
    private String rol;

    // Getters y Setters
}
