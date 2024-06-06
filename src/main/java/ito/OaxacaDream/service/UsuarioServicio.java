package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Usuario;
import ito.OaxacaDream.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServicio implements IUsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Override
    public List<Usuario> listarUsuario() {
        return this.usuarioRepositorio.findAll();
    }
    @Override
    public Usuario buscarUsuarioPorId(Integer idUusario) {
        Usuario usuario =
                this.usuarioRepositorio.findById(idUusario).orElse(null);
        return usuario;
    }
    @Override
    public Usuario guardarUsuario(Usuario usuario) {

        return this.usuarioRepositorio.save(usuario);
    }
    @Override
    public void eliminarUsuarioPorId(Integer idUusario) {
        this.usuarioRepositorio.deleteById(idUusario);
    }
}
