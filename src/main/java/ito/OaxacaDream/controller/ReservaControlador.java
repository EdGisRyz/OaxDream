package ito.OaxacaDream.controller;

import ito.OaxacaDream.exceptions.RecursoNoEncontradoException;
import ito.OaxacaDream.models.Reserva;
import ito.OaxacaDream.models.Tour;
import ito.OaxacaDream.models.Usuario;
import ito.OaxacaDream.service.ReservaServicio;
import ito.OaxacaDream.service.TourServicio;
import ito.OaxacaDream.service.UsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/oaxacaDream-app
@RequestMapping("oaxacaDream-app")
@CrossOrigin(value = "http://localhost:4200")
public class ReservaControlador {

    private static final Logger logger = LoggerFactory.getLogger(ReservaControlador.class);

    @Autowired
    private ReservaServicio reservaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private TourServicio tourServicio;

    //ENDPOINTS PARA USUARIOS
    @GetMapping("/reservas")
    public List<Reserva> obtenerReservas(){
        List<Reserva> reservas = this.reservaServicio.listarReservas();
        logger.info("Reservas Obtenidos: ");
        reservas.forEach((reserva -> logger.info(reserva.toString())));
        return reservas;
    }

    @GetMapping("/reservas/{id}")
    public ResponseEntity<Reserva> obtenerReservaPorId(@PathVariable int id){
        Reserva usuario = this.reservaServicio.buscarReservaPorId(id);
        if(usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

    @PostMapping(path = "/reservas")
    public ResponseEntity<Reserva> agregarReserva(@RequestBody Reserva reserva){
        logger.info("Usuario A agregar: " + reserva);
        return ResponseEntity.ok(this.reservaServicio.guardarReserva(reserva));
    }

    @PutMapping(path = "/reservas/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable int id, @RequestBody Reserva reservaRecibida){
        Reserva reserva = this.reservaServicio.buscarReservaPorId(id);
        if(reserva == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }

        Usuario usuario = this.usuarioServicio.buscarUsuarioPorId(reservaRecibida.getUsuario().getIdUsuario());
        Tour tour = this.tourServicio.buscarTourPorId(reservaRecibida.getTour().getIdTour());

        if(usuario != null){
            reserva.setUsuario(usuario);
        }
        if(tour != null){
            reserva.setTour(tour);
        }

        reserva.setFechaReserva(reservaRecibida.getFechaReserva());
        reserva.setEstado(reservaRecibida.getEstado());
        reserva.setCantidad(reservaRecibida.getCantidad());

        this.reservaServicio.guardarReserva(reserva);
        return ResponseEntity.ok(reserva);
    }

    @DeleteMapping("/reservas/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarReserva(@PathVariable int id){
        Reserva reserva = reservaServicio.buscarReservaPorId(id);
        if(reserva == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        this.reservaServicio.eliminarReservaPorId(reserva.getIdReserva());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
