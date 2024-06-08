package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Reserva;

import java.util.List;

public interface IReservaServicio {

    public List<Reserva> listarReservas();
    public Reserva buscarReservaPorId(Integer idReserva);
    public Reserva guardarReserva(Reserva reserva);
    public void eliminarReservaPorId(Integer idReserva);

}
