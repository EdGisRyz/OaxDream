package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Reserva;
import ito.OaxacaDream.repository.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServicio implements IReservaServicio{

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    @Override
    public List<Reserva> listarReservas() {
        return this.reservaRepositorio.findAll();
    }

    @Override
    public Reserva buscarReservaPorId(Integer idReserva) {
        return this.reservaRepositorio.findById(idReserva).orElse(null);
    }

    @Override
    public Reserva guardarReserva(Reserva reserva) {
        return this.reservaRepositorio.save(reserva);
    }

    @Override
    public void eliminarReservaPorId(Integer idReserva) {
        this.reservaRepositorio.deleteById(idReserva);
    }
}
