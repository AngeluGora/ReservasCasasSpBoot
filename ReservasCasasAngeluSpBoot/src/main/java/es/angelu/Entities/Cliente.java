package es.angelu.Entities;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "clientes")
@Scope("singleton")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientes_seq")
    @SequenceGenerator(name = "clientes_seq", sequenceName = "SEQ_CLIENTE_ID", allocationSize = 1)
    @Column(name = "ID_CLI")
    private Long idCli;

    private String nombre;
    private String email;
    private String telefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    public Cliente(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }
}
