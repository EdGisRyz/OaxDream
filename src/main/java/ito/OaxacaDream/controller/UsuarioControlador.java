package ito.OaxacaDream.controller;

import ito.OaxacaDream.models.Usuario;
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
@RequestMapping("oaxacaDream-app")
@CrossOrigin(value = "http://localhost:4200")
public class UsuarioControlador {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private UsuarioServicio usuarioServicio;

    //ENDPOINTS PARA USUARIOS
    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios(){
        List<Usuario> usuarios = this.usuarioServicio.listarUsuarios();
        logger.info("Usuarios Obtenidos: ");
        usuarios.forEach((usuario -> logger.info(usuarios.toString())));
        return usuarios;
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable int id){
        Usuario usuario = this.usuarioServicio.buscarUsuarioPorId(id);
        if(usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

    @PostMapping(path = "/usuarios")
    public ResponseEntity<Usuario> agregarUsuario(@RequestBody Usuario usuario){
        logger.info("Usuario A agregar: " + usuario);
        return ResponseEntity.ok(this.usuarioServicio.guardarUsuario(usuario));
    }

    @PutMapping(path = "/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioRecibido){
        Usuario usuario = this.usuarioServicio.buscarUsuarioPorId(id);
        if(usuario == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        // Actualizar los campos que se permitan
        usuario.setNombre(usuarioRecibido.getNombre());
        usuario.setApPaterno(usuarioRecibido.getApPaterno());
        usuario.setApMaterno(usuarioRecibido.getApMaterno());
        usuario.setEmail(usuarioRecibido.getEmail());
        usuario.setContrasena(usuarioRecibido.getContrasena());
        usuario.setTelefono(usuarioRecibido.getTelefono());
        usuario.setTipo(usuarioRecibido.getTipo());
        usuario.setFechaRegistro(usuarioRecibido.getFechaRegistro());
        usuario.setDireccion(usuarioRecibido.getDireccion());

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


}
