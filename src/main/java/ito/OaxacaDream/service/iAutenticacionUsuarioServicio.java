package ito.OaxacaDream.service;

import ito.OaxacaDream.models.AutenticacionUsuario;
import ito.OaxacaDream.models.Usuario;
import java.util.List;
public interface iAutenticacionUsuarioServicio {
    public List<AutenticacionUsuario> listarAutenticacionUsuarios();
    public AutenticacionUsuario buscarAutenticacionUsuarioPorId(Integer idUsuario);
    public void guardarAutenticacionUsuario(AutenticacionUsuario autenticacionUsuario);
    public void eliminaarAutenticacionPoId(Integer idUsuario);
}
