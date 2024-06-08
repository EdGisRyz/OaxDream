package ito.OaxacaDream.controller;

import ito.OaxacaDream.exceptions.RecursoNoEncontradoException;
import ito.OaxacaDream.models.MetodoPago;
import ito.OaxacaDream.models.Pago;
import ito.OaxacaDream.models.Reserva;
import ito.OaxacaDream.service.MetodoPagoServicio;
import ito.OaxacaDream.service.PagoServicio;
import ito.OaxacaDream.service.ReservaServicio;
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
public class PagoControlador {

    private static final Logger logger = LoggerFactory.getLogger(PagoControlador.class);

    @Autowired
    private PagoServicio pagoServicio;

    @Autowired
    private ReservaServicio reservaServicio;

    @Autowired
    private MetodoPagoServicio metodoPagoServicio;

    //ENDPOINTS PARA USUARIOS
    @GetMapping("/pagos")
    public List<Pago> obtenerPagos(){
        List<Pago> pagos = this.pagoServicio.listarPagos();
        logger.info("Usuarios Obtenidos: ");
        pagos.forEach((pago -> logger.info(pago.toString())));
        return pagos;
    }

    @GetMapping("/pagos/{id}")
    public ResponseEntity<Pago> obtenerPagoPorId(@PathVariable int id){
        Pago pago = this.pagoServicio.buscarPagoPorId(id);
        if(pago != null) {
            return ResponseEntity.ok(pago);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

    @PostMapping(path = "/pagos")
    public ResponseEntity<Pago> agregarPago(@RequestBody Pago pago){
        logger.info("Pago a agregar: " + pago);
        return ResponseEntity.ok(this.pagoServicio.guardarPago(pago));
    }

    @PutMapping(path = "/pagos/{id}")
    public ResponseEntity<Pago> actualizarPago(@PathVariable int id, @RequestBody Pago pagoRecibido){
        Pago pago = this.pagoServicio.buscarPagoPorId(id);
        if(pago == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }

        Reserva reserva = this.reservaServicio.buscarReservaPorId(pagoRecibido.getReserva().getIdReserva());
        MetodoPago metodoPago = this.metodoPagoServicio.buscarMetodoPorId(pagoRecibido.getMetodoPago().getIdMetodoPago());

        if(reserva != null){
            pago.setReserva(reserva);
        }
        if(metodoPago != null){
            pago.setMetodoPago(metodoPago);
        }

        pago.setMonto(pagoRecibido.getMonto());
        pago.setFechaPago(pagoRecibido.getFechaPago());
        pago.setEstado(pagoRecibido.getEstado());

        this.pagoServicio.guardarPago(pago);
        return ResponseEntity.ok(pago);
    }

    @DeleteMapping("/pagos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarPago(@PathVariable int id){
        Pago pago = pagoServicio.buscarPagoPorId(id);
        if(pago == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        this.pagoServicio.eliminarPagoPorId(pago.getIdPago());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
