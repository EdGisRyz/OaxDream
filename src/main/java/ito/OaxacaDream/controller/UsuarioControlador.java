package ito.OaxacaDream.controller;

import ito.OaxacaDream.exceptions.cupoCompletoException;
import ito.OaxacaDream.models.AutenticacionUsuario;
import ito.OaxacaDream.models.Tour;
import ito.OaxacaDream.models.Usuario;
import ito.OaxacaDream.service.AutenticacionUsuarioServicio;
import ito.OaxacaDream.service.TourServicio;
import ito.OaxacaDream.service.UsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ito.OaxacaDream.exceptions.RecursoNoEncontradoException;

import java.util.*;


@RestController
//http://localhost:8080/OaxacaDream-app
@RequestMapping("OaxacaDream-app")
@CrossOrigin(value = "http://localhost:4200")
public class UsuarioControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(UsuarioControlador.class);
    @Autowired
    private UsuarioServicio usuarioServicio;
    // http://localhost:8080/OaxacaDream-app/usuarios

    @Autowired
    private AutenticacionUsuarioServicio autenticacionUsuarioServicio;
    // http://localhost:8080/OaxacaDream-app/usuarios

    @Autowired
    private TourServicio tourServicio;



    //ENDPOINTS PARA USUARIOS
    @GetMapping("/usuarios")
    public List<Usuario> obtenerUusarios(){
        List<Usuario> usuarios = this.usuarioServicio.listarUsuario();
        logger.info("Usuarios Obtenidos: ");
        usuarios.forEach((usuario -> logger.info(usuarios.toString())));
        return usuarios;
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerClientePorId(@PathVariable int id){
        Usuario usuario = this.usuarioServicio.buscarUsuarioPorId(id);
        if(usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

    @PostMapping(path = "/usuarios", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> agregarUsuario(@RequestBody Usuario usuario){
        logger.info("Usuario A agregar: " + usuario);
        return ResponseEntity.ok(this.usuarioServicio.guardarUsuario(usuario));
    }

    @PutMapping(path = "/usuarios/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioRecibido){
        Usuario usuario = this.usuarioServicio.buscarUsuarioPorId(id);
        if(usuario == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        // Actualizar los campos que se permitan
        usuario.setNombre(usuarioRecibido.getNombre());
        usuario.setApellido_Paterno(usuarioRecibido.getApellido_Paterno());
        usuario.setApellido_Materno(usuarioRecibido.getApellido_Materno());
        usuario.setCorreo_Electronico(usuarioRecibido.getCorreo_Electronico());
        usuario.setContrasena(usuarioRecibido.getContrasena());
        usuario.setTelefono(usuarioRecibido.getTelefono());

        List<Tour> toursBd = this.tourServicio.listarTour();
        Set<Tour> tours = usuarioRecibido.getTours();

        Set<Tour> activos = new HashSet<>(toursBd);

        toursBd.removeIf(tour -> !activos.contains(tour));
        usuarioRecibido.setTours(tours);

        usuario.setTours(usuarioRecibido.getTours());

        this.usuarioServicio.guardarUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable int id){
        Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);
        if(usuario == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        this.usuarioServicio.eliminarUsuarioPorId(usuario.getIdUsuario());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }


    //ENDPOINTS PARA AutenticacionUsuarios
    @GetMapping("/autenticacionUsuarios")
    public List<AutenticacionUsuario> optenerAutenticacionUsuarios(){
        List<AutenticacionUsuario> autenticacionUsuarios = this.autenticacionUsuarioServicio.listarAutenticacionUsuarios();
        logger.info("Autenticacion de Usuarios Obtenidos: ");
        autenticacionUsuarios.forEach((autenticacionUsuario -> logger.info(autenticacionUsuarios.toString())));
        return autenticacionUsuarios;
    }


}
