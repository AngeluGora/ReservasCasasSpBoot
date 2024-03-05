package es.angelu.Entities;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import java.util.Date;

@Component
@Entity
@Table(name = "reservas")
@Scope("singleton")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservas_seq")
    @SequenceGenerator(name = "reservas_seq", sequenceName = "SEQ_RESERVA_ID", allocationSize = 1)
    @Column(name = "ID_R")
    private Long idR;

    @ManyToOne
    @JoinColumn(name = "ID_CLI", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ID_C", nullable = false)
    private Casa casa;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin;

    public Reserva(Cliente cliente, Casa casa, Date fechaInicio, Date fechaFin) {
        this.cliente = cliente;
        this.casa = casa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
}
