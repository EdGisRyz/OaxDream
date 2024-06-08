package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Tour;
import ito.OaxacaDream.repository.TourRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class TourServicio implements ITourServicio {

    @Autowired
    private TourRepositorio tourRepositorio;

    @Override
    public List<Tour> listarTours() {
        return this.tourRepositorio.findAll();
    }

    @Override
    public Tour buscarTourPorId(Integer idTour) {
        return this.tourRepositorio.findById(idTour).orElse(null);
    }

    @Override
    public Tour guardarTour(Tour tour) {return this.tourRepositorio.save(tour);  }

    @Override
    public void eliminarTourPorId(Integer idTour) {
        this.tourRepositorio.deleteById(idTour);
    }
}
