package ito.OaxacaDream.controller;

import ito.OaxacaDream.exceptions.RecursoNoEncontradoException;
import ito.OaxacaDream.models.Comentario;
import ito.OaxacaDream.models.Tour;
import ito.OaxacaDream.models.Usuario;
import ito.OaxacaDream.service.ComentarioServicio;
import ito.OaxacaDream.service.TourServicio;
import ito.OaxacaDream.service.UsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/oaxacaDream-app
@RequestMapping("oaxacaDream-app")
@CrossOrigin(value = "http://localhost:4200")
public class ComentarioControlador {

    private static final Logger logger = LoggerFactory.getLogger(ComentarioControlador.class);

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private TourServicio tourServicio;

    //ENDPOINTS PARA USUARIOS
    @GetMapping("/comentarios")
    public List<Comentario> obtenerComentarios(){
        List<Comentario> comentarios = this.comentarioServicio.listarComentarios();
        logger.info("Reservas Obtenidos: ");
        comentarios.forEach((comentario -> logger.info(comentario.toString())));
        return comentarios;
    }

    @GetMapping("/comentarios/{id}")
    public ResponseEntity<Comentario> obtenerComentarioPorId(@PathVariable int id){
        Comentario comentario = this.comentarioServicio.buscarComentarioPorId(id);
        if(comentario != null) {
            return ResponseEntity.ok(comentario);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

    @PostMapping(path = "/comentarios")
    public ResponseEntity<Comentario> agregarReserva(@RequestBody Comentario comentario){
        logger.info("Usuario A agregar: " + comentario);
        return ResponseEntity.ok(this.comentarioServicio.guardarComentario(comentario));
    }

    @PutMapping(path = "/comentarios/{id}")
    public ResponseEntity<Comentario> actualizarReserva(@PathVariable int id, @RequestBody Comentario comentarioRecibido){
        Comentario comentario = this.comentarioServicio.buscarComentarioPorId(id);
        if(comentario == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }

        Usuario usuario = this.usuarioServicio.buscarUsuarioPorId(comentarioRecibido.getUsuario().getIdUsuario());
        Tour tour = this.tourServicio.buscarTourPorId(comentarioRecibido.getTour().getIdTour());

        if(usuario != null){
            comentario.setUsuario(usuario);
        }
        if(tour != null){
            comentario.setTour(tour);
        }

        comentario.setTitulo(comentarioRecibido.getTitulo());
        comentario.setComentario(comentarioRecibido.getComentario());
        comentario.setCalificacion(comentarioRecibido.getCalificacion());
        comentario.setFechaComentario(comentarioRecibido.getFechaComentario());

        this.comentarioServicio.guardarComentario(comentario);
        return ResponseEntity.ok(comentario);
    }

    @DeleteMapping("/comentarios/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarComentario(@PathVariable int id){
        Comentario comentario = comentarioServicio.buscarComentarioPorId(id);
        if(comentario == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        this.comentarioServicio.eliminarComentarioPorId(comentario.getIdComentario());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
