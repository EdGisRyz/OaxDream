package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Tour;
import ito.OaxacaDream.models.Usuario;

import java.util.List;

public interface iTourServicio {
    public List<Tour> listarTour();
    public Tour buscarTourPorId(Integer idTour);
    public void guardarTour(Tour tour);
    public void eliminarTourPorId(Integer idTour);
}
