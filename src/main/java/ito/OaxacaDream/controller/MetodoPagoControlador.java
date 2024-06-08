package ito.OaxacaDream.controller;

import ito.OaxacaDream.exceptions.RecursoNoEncontradoException;
import ito.OaxacaDream.models.MetodoPago;
import ito.OaxacaDream.service.MetodoPagoServicio;
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
public class MetodoPagoControlador {

    private static final Logger logger = LoggerFactory.getLogger(MetodoPagoControlador.class);

    @Autowired
    private MetodoPagoServicio metodoPagoServicio;

    //ENDPOINTS PARA USUARIOS
    @GetMapping("/metodos")
    public List<MetodoPago> obtenerMetodos(){
        List<MetodoPago> metodosPago = this.metodoPagoServicio.listarMetodos();
        logger.info("Metodos Obtenidos: ");
        metodosPago.forEach((metodo -> logger.info(metodo.toString())));
        return metodosPago;
    }

    @GetMapping("/metodos/{id}")
    public ResponseEntity<MetodoPago> obtenerMetodoPorId(@PathVariable int id){
        MetodoPago metodoPago = this.metodoPagoServicio.buscarMetodoPorId(id);
        if(metodoPago != null) {
            return ResponseEntity.ok(metodoPago);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

    @PostMapping(path = "/metodos")
    public ResponseEntity<MetodoPago> agregarMetodo(@RequestBody MetodoPago metodoPago){
        logger.info("Usuario A agregar: " + metodoPago);
        return ResponseEntity.ok(this.metodoPagoServicio.guardarMetodo(metodoPago));
    }

    @PutMapping(path = "/metodos/{id}")
    public ResponseEntity<MetodoPago> actualizarMetodo(@PathVariable int id, @RequestBody MetodoPago metodoRecbido){
        MetodoPago metodoPago = this.metodoPagoServicio.buscarMetodoPorId(id);
        if(metodoPago == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        // Actualizar los campos que se permitan
        metodoPago.setNombre(metodoRecbido.getNombre());

        this.metodoPagoServicio.guardarMetodo(metodoPago);
        return ResponseEntity.ok(metodoPago);
    }

    @DeleteMapping("/metodos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarMetodo(@PathVariable int id){
        MetodoPago metodoPago = metodoPagoServicio.buscarMetodoPorId(id);
        if(metodoPago == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        this.metodoPagoServicio.eliminarMetodoPorId(metodoPago.getIdMetodoPago());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
