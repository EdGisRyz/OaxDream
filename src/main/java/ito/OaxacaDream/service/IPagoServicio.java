package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Pago;

import java.util.List;

public interface IPagoServicio {

    public List<Pago> listarPagos();
    public Pago buscarPagoPorId(Integer idPago);
    public Pago guardarPago(Pago pago);
    public void eliminarPagoPorId(Integer idPago);
}
