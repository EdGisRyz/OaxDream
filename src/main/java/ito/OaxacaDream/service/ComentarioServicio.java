package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Comentario;
import ito.OaxacaDream.repository.ComentarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioServicio implements IComentarioServicio{

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    @Override
    public List<Comentario> listarComentarios() {
        return this.comentarioRepositorio.findAll();
    }

    @Override
    public Comentario buscarComentarioPorId(Integer idComentario) {
        return this.comentarioRepositorio.findById(idComentario).orElse(null);
    }

    @Override
    public Comentario guardarComentario(Comentario comentario) {
        return this.comentarioRepositorio.save(comentario);
    }

    @Override
    public void eliminarComentarioPorId(Integer idComentario) {
        this.comentarioRepositorio.deleteById(idComentario);
    }
}
