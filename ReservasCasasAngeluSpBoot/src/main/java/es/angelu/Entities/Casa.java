package es.angelu.Entities;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "casas")
@Scope("singleton")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Casa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "casas_seq")
    @SequenceGenerator(name = "casas_seq", sequenceName = "SEQ_CASA_ID", allocationSize = 1)
    @Column(name = "ID_C")
    private Long idC;

    private String direccion;
    private int numeroHabitaciones;
    private double precioPorNoche;

    @OneToMany(mappedBy = "casa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    public Casa(String direccion, int numeroHabitaciones, double precioPorNoche) {
        this.direccion = direccion;
        this.numeroHabitaciones = numeroHabitaciones;
        this.precioPorNoche = precioPorNoche;
    }
}
