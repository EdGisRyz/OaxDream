package ito.OaxacaDream.service;

import ito.OaxacaDream.models.AutenticacionUsuario;
import ito.OaxacaDream.repository.AutenticacionUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutenticacionUsuarioServicio implements iAutenticacionUsuarioServicio{
    @Autowired
    private AutenticacionUsuarioRepositorio autenticacionUsuarioRepositorio;
    @Override
    public List<AutenticacionUsuario> listarAutenticacionUsuarios() {
        return this.autenticacionUsuarioRepositorio.findAll();
    }
    @Override
    public AutenticacionUsuario buscarAutenticacionUsuarioPorId(Integer idUsuario) {
        AutenticacionUsuario autenticacionUsuario =
                this.autenticacionUsuarioRepositorio.findById(idUsuario).orElse(null);
        return autenticacionUsuario;
    }
    @Override
    public void guardarAutenticacionUsuario(AutenticacionUsuario autenticacionUsuario) {
        this.autenticacionUsuarioRepositorio.save(autenticacionUsuario);
    }
    @Override
    public void eliminaarAutenticacionPoId(Integer idUsuario) {
        this.autenticacionUsuarioRepositorio.deleteById(idUsuario);
    }
}
