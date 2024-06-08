package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Tour;

import java.util.List;

public interface ITourServicio {
    public List<Tour> listarTours();
    public Tour buscarTourPorId(Integer idTour);
    public Tour guardarTour(Tour tour);
    public void eliminarTourPorId(Integer idTour);
}
