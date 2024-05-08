package ito.OaxacaDream.controller;

import ito.OaxacaDream.models.Tour;
import ito.OaxacaDream.service.TourServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
