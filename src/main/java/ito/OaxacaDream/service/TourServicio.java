package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Tour;
import ito.OaxacaDream.repository.TourRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class TourServicio implements iTourServicio {

    @Autowired
    private TourRepositorio tourRepositorio;
    @Override
    public List<Tour> listarTour() {
        return this.tourRepositorio.findAll();
    }

    @Override
    public Tour buscarTourPorId(Integer idTour) {
        Tour tour =
                this.tourRepositorio.findById(idTour).orElse(null);
        return tour;
    }

    @Override
    public void guardarTour(Tour tour) {
        this.tourRepositorio.save(tour);
    }

    @Override
    public void eliminarTourPorId(Integer idTour) {
        this.tourRepositorio.deleteById(idTour);
    }
}
