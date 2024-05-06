package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Usuario;
import java.util.List;
public interface IUsuarioServicio {
    public List<Usuario> listarUsuario();
    public Usuario buscarProductoPorId(Integer idUusario);
    public void guardarUsuario(Usuario usuario);
    public void eliminarUsuarioPorId(Integer idUusario);
}
