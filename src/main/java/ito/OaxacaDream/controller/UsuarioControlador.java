package ito.OaxacaDream.controller;

import ito.OaxacaDream.models.AutenticacionUsuario;
import ito.OaxacaDream.models.Usuario;
import ito.OaxacaDream.service.AutenticacionUsuarioServicio;
import ito.OaxacaDream.service.UsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
//http://localhost:8080/OaxacaDream-app
@RequestMapping("OaxacaDream-app")
@CrossOrigin(value = "http://localhost4200")
public class UsuarioControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(UsuarioControlador.class);
    @Autowired
    private UsuarioServicio usuarioServicio;
    // http://localhost:8080/OaxacaDream-app/usuarios
    @GetMapping("/Usuarios")
    public List<Usuario> obtenerUusarios(){
        List<Usuario> usuarios = this.usuarioServicio.listarUsuario();
        logger.info("Usuarios Obtenidos: ");
        usuarios.forEach((usuario -> logger.info(usuarios.toString())));
        return usuarios;
    }

    @Autowired
    private AutenticacionUsuarioServicio autenticacionUsuarioServicio;

    // http://localhost:8080/OaxacaDream-app/usuarios
    @GetMapping("/AutenticacionUsuarios")
    public List<AutenticacionUsuario> optenerAutenticacionUsuarios(){
        List<AutenticacionUsuario> autenticacionUsuarios = this.autenticacionUsuarioServicio.listarAutenticacionUsuarios();
        logger.info("Autenticacion de Usuarios Obtenidos: ");
        autenticacionUsuarios.forEach((autenticacionUsuario -> logger.info(autenticacionUsuarios.toString())));
        return autenticacionUsuarios;
    }


}
