package ito.OaxacaDream.controller;

import ito.OaxacaDream.exceptions.RecursoNoEncontradoException;
import ito.OaxacaDream.models.Empresa;
import ito.OaxacaDream.service.EmpresaServicio;
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
public class EmpresaControlador {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaControlador.class);

    @Autowired
    private EmpresaServicio empresaServicio;

    //ENDPOINTS PARA USUARIOS
    @GetMapping("/empresas")
    public List<Empresa> obtenerEmpresas(){
        List<Empresa> empresas = this.empresaServicio.listarEmpresas();
        logger.info("Usuarios Obtenidos: ");
        empresas.forEach((empresa -> logger.info(empresa.toString())));
        return empresas;
    }

    @GetMapping("/empresas/{id}")
    public ResponseEntity<Empresa> obtenerEmpresaPorId(@PathVariable int id){
        Empresa empresa = this.empresaServicio.buscarEmpresaPorId(id);
        if(empresa != null) {
            return ResponseEntity.ok(empresa);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
    }

    @PostMapping(path = "/empresas")
    public ResponseEntity<Empresa> agregarEmpresa(@RequestBody Empresa empresa){
        logger.info("Usuario A agregar: " + empresa);
        return ResponseEntity.ok(this.empresaServicio.guardarEmpresa(empresa));
    }

    @PutMapping(path = "/empresas/{id}")
    public ResponseEntity<Empresa> actualizarEmpresa(@PathVariable int id, @RequestBody Empresa empresaRecibida){
        Empresa empresa = this.empresaServicio.buscarEmpresaPorId(id);
        if(empresa == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        // Actualizar los campos que se permitan
        empresa.setNombre(empresaRecibida.getNombre());
        empresa.setSlogan(empresaRecibida.getSlogan());
        empresa.setVision(empresaRecibida.getVision());
        empresa.setMision(empresaRecibida.getMision());
        empresa.setValores(empresaRecibida.getValores());
        empresa.setTrayectoria(empresaRecibida.getTrayectoria());
        empresa.setFuturo(empresaRecibida.getFuturo());

        this.empresaServicio.guardarEmpresa(empresa);
        return ResponseEntity.ok(empresa);
    }

    @DeleteMapping("/empresas/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpresa(@PathVariable int id){
        Empresa empresa = empresaServicio.buscarEmpresaPorId(id);
        if(empresa == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }
        this.empresaServicio.eliminarEmpresaPorId(empresa.getIdEmpresa());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
