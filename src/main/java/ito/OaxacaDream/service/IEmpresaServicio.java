package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Empresa;

import java.util.List;

public interface IEmpresaServicio {

    public List<Empresa> listarEmpresas();
    public Empresa buscarEmpresaPorId(Integer idEmpresa);
    public Empresa guardarEmpresa(Empresa empresa);
    public void eliminarEmpresaPorId(Integer idEmpresa);

}
