package ito.OaxacaDream.service;

import ito.OaxacaDream.models.MetodoPago;

import java.util.List;

public interface IMetodoPagoServicio {

    public List<MetodoPago> listarMetodos();
    public MetodoPago buscarMetodoPorId(Integer idMetodoPago);
    public MetodoPago guardarMetodo(MetodoPago metodoPago);
    public void eliminarMetodoPorId(Integer idMetodoPago);
}
