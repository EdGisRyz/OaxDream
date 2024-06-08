package ito.OaxacaDream.service;

import ito.OaxacaDream.models.Comentario;

import java.util.List;

public interface IComentarioServicio {
    public List<Comentario> listarComentarios();
    public Comentario buscarComentarioPorId(Integer idComentario);
    public Comentario guardarComentario(Comentario comentario);
    public void eliminarComentarioPorId(Integer idComentario);
}
