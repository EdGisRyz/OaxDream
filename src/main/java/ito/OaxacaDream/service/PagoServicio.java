package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Pago;
import ito.OaxacaDream.repository.PagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServicio implements IPagoServicio{

    @Autowired
    private PagoRepositorio pagoRepositorio;

    @Override
    public List<Pago> listarPagos() {
        return this.pagoRepositorio.findAll();
    }

    @Override
    public Pago buscarPagoPorId(Integer idPago) {
        return this.pagoRepositorio.findById(idPago).orElse(null);
    }

    @Override
    public Pago guardarPago(Pago pago) {
        return this.pagoRepositorio.save(pago);
    }

    @Override
    public void eliminarPagoPorId(Integer idPago) {
        this.pagoRepositorio.deleteById(idPago);
    }
}
