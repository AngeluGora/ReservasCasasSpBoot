package es.Pruebas;

import es.angelu.Entities.Casa;
import es.angelu.Entities.Cliente;
import es.angelu.Entities.Reserva;
import es.angelu.Entities.Usuario;
import es.angelu.Repository.CasasDAO;
import es.angelu.Repository.ClientesDAO;
import es.angelu.Repository.ReservasDAO;
import es.angelu.Repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
@Slf4j
public class App01_InsertarDatosPrueba implements CommandLineRunner {

    CasasDAO casasDAO;
    ClientesDAO clientesDAO;
    ReservasDAO reservasDAO;
    UsuarioRepository usuarioRepository;

    Casa casa;
    Cliente cliente;
    Reserva reserva;
    Usuario usuario;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public App01_InsertarDatosPrueba(UsuarioRepository usuarioRepository, CasasDAO casasDAO, ClientesDAO clientesDAO, ReservasDAO reservasDAO) {
        this.usuarioRepository=usuarioRepository;
        this.casasDAO = casasDAO;
        this.clientesDAO = clientesDAO;
        this.reservasDAO = reservasDAO;
    }

    public static void main(String[] args) {
        SpringApplication.run(App01_InsertarDatosPrueba.class, args);
    }

    @Override
    public void run(String[] args) throws Exception {
        // Insertar datos de prueba para Casas
        var casa1 = Casa.builder()
                .direccion("Calle Olvidada 5, Narnia")
                .numeroHabitaciones(5)
                .precioPorNoche(90)
                .build();
        var casa2 = Casa.builder()
                .direccion("Calle Fantastica 13, Japon")
                .numeroHabitaciones(5)
                .precioPorNoche(90)
                .build();

        casasDAO.save(casa1);
        casasDAO.save(casa2);

        var cliente1 = Cliente.builder()
                .nombre("Juan")
                .email("juan@bosco.es")
                .telefono("123456789")
                .build();
        var cliente2 = Cliente.builder()
                .nombre("Maria")
                .email("maria@bosco.es")
                .telefono("987654321")
                .build();


        clientesDAO.save(cliente1);
        clientesDAO.save(cliente2);

        var reserva1 = Reserva.builder()
                .cliente(cliente1)
                .casa(casa1)
                .fechaInicio(new Date(2024, 1, 29))
                .fechaFin(new Date(2024, 2, 5))
                .build();
        var reserva2 = Reserva.builder()
                .cliente(cliente2)
                .casa(casa2)
                .fechaInicio(new Date(2024, 2, 10))
                .fechaFin(new Date(2024, 2, 15))
                .build();

        reservasDAO.save(reserva1);
        reservasDAO.save(reserva2);

        String contrasenaEncriptada = passwordEncoder.encode("1234");
        String contrasenaEncriptada2 = passwordEncoder.encode("1234");
        var u1 = Usuario.builder()
                .nombreUsuario("admin")
                .password(contrasenaEncriptada)
                .rol("ADMIN")
                .build();
        var u2 = Usuario.builder()
                .nombreUsuario("angelu")
                .password(contrasenaEncriptada2)
                .rol("USER")
                .build();
        usuarioRepository.save(u1);
        usuarioRepository.save(u2);

        // Mostrar datos insertados en log
        log.info("Casas insertadas: {}", casasDAO.findAll());
        log.info("Clientes insertados: {}", clientesDAO.findAll());
        log.info("Reservas insertadas: {}", reservasDAO.findAll());
        log.info("Ususarios insertados: {}", usuarioRepository.findAll());

    }
}
