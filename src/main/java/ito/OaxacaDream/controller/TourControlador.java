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
//http://localhost:8080/oaxacaDream-app
@RequestMapping("oaxacaDream-app")
@CrossOrigin(value = "http://localhost:4200")
public class TourControlador {

    private static final Logger logger = LoggerFactory.getLogger(TourControlador.class);

    @Autowired
    private TourServicio tourServicio;

    // http://localhost:8081/OaxacaDream-app/tours
    @GetMapping("/tours")
    public List<Tour> obtenerTours(){
        List<Tour> tours = this.tourServicio.listarTours();
        logger.info("Tours Obtenidos: ");
        tours.forEach((tour -> logger.info(tour.toString())));
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

    @PostMapping(path = "/tours")
    public ResponseEntity<Tour> agregaTour(@RequestBody Tour tour){
        logger.info("Tour A agregar: " + tour);
        return ResponseEntity.ok(this.tourServicio.guardarTour(tour));
    }

    @PutMapping(path = "/tours/{id}")
    public ResponseEntity<Tour> actualizarTour(@PathVariable int id, @RequestBody Tour tourRecibido){
        Tour tour = this.tourServicio.buscarTourPorId(id);
        if(tour == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }

        tour.setNombre(tourRecibido.getNombre());
        tour.setDescripcion(tourRecibido.getDescripcion());
        tour.setDuracion(tourRecibido.getDuracion());
        tour.setPrecio(tourRecibido.getPrecio());
        tour.setCapacidadMax(tourRecibido.getCapacidadMax());
        tour.setFechaCreacion(tourRecibido.getFechaCreacion());
        tour.setLocalizacion(tourRecibido.getLocalizacion());

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
