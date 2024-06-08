package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Usuario;
import java.util.List;
public interface IUsuarioServicio {
    public List<Usuario> listarUsuarios();
    public Usuario buscarUsuarioPorId(Integer idUusario);
    public Usuario guardarUsuario(Usuario usuario);
    public void eliminarUsuarioPorId(Integer idUusario);
}
