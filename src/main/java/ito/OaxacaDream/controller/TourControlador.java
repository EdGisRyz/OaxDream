package ito.OaxacaDream.controller;

import ito.OaxacaDream.exceptions.RecursoNoEncontradoException;
import ito.OaxacaDream.models.Tour;
import ito.OaxacaDream.models.Usuario;
import ito.OaxacaDream.service.TourServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("OaxacaDream-app")
@CrossOrigin(value = "http://localhost4200")
public class TourControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(TourControlador.class);
    @Autowired
    private TourServicio tourServicio;

    // http://localhost:8081/OaxacaDream-app/tours
    @GetMapping("/tours")
    public List<Tour> obtenerTours(){
        List<Tour> tours = this.tourServicio.listarTour();
        logger.info("Tours Obtenidos: ");
        tours.forEach((tour -> logger.info(tours.toString())));
        return tours;
    }

    @GetMapping("/tours/{id}")
    public ResponseEntity<Tour> obtenerTourPorId(@PathVariable int id){
        Tour tour = this.tourServicio.buscarTourPorId(id);
        if(tour != null) {
            return ResponseEntity.ok(tour);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

    @PostMapping(path = "/tours", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tour> agregaTour(@RequestBody Tour tour){
        logger.info("Tour A agregar: " + tour);
        return ResponseEntity.ok(this.tourServicio.guardarTour(tour));
    }

    @PutMapping(path = "/tours/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tour> actualizarTour(@PathVariable int id, @RequestBody Tour tourRecibido){
        Tour tour = this.tourServicio.buscarTourPorId(id);
        if(tour == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }

        //usuario.setTelefono(usuarioRecibido.getTelefono());

        tour.setIdTour(tourRecibido.getIdTour());
        tour.setNombreDelTour(tourRecibido.getNombreDelTour());
        tour.setDetalles(tourRecibido.getDetalles());
        tour.setPrecio(tourRecibido.getPrecio());



        this.tourServicio.guardarTour(tour);
        return ResponseEntity.ok(tour);
    }

    @DeleteMapping("/tours/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarTour(@PathVariable int id){
        Tour tour = tourServicio.buscarTourPorId(id);
        if(tour == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        this.tourServicio.eliminarTourPorId(tour.getIdTour());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
