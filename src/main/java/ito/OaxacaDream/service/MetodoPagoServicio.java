package ito.OaxacaDream.service;

import ito.OaxacaDream.models.MetodoPago;
import ito.OaxacaDream.repository.MetodoPagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodoPagoServicio implements IMetodoPagoServicio{

    @Autowired
    private MetodoPagoRepositorio metodoPagoRepositorio;

    @Override
    public List<MetodoPago> listarMetodos() {
        return this.metodoPagoRepositorio.findAll();
    }

    @Override
    public MetodoPago buscarMetodoPorId(Integer idMetodoPago) {
        return this.metodoPagoRepositorio.findById(idMetodoPago).orElse(null);
    }

    @Override
    public MetodoPago guardarMetodo(MetodoPago metodoPago) {
        return this.metodoPagoRepositorio.save(metodoPago);
    }

    @Override
    public void eliminarMetodoPorId(Integer idMetodoPago) {
        this.metodoPagoRepositorio.deleteById(idMetodoPago);
    }
}
