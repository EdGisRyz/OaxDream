package ito.OaxacaDream.controller;

import ito.OaxacaDream.exceptions.RecursoNoEncontradoException;
import ito.OaxacaDream.models.Pago;
import ito.OaxacaDream.service.PagoServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;


@RestController
//http:/localhost:8080/felinus-app
@RequestMapping("oaxacaDream-app")
//configuramos para que angular pueda realizar peticiones a nuestro backend
@CrossOrigin(value="http://localhost:4200")
public class ImgComprobanteControlador {

    private static final Logger logger = LoggerFactory.getLogger(ImgComprobanteControlador.class);

    @Autowired
    private PagoServicio pagoServicio;

    // agregar imagen a prendas
    @PutMapping(path = "/img-pago/{id}")
    public ResponseEntity<Pago> agregarComprobantePago(
            @PathVariable int id,
            HttpServletRequest request,
            @RequestParam("image") MultipartFile file
    ) throws IOException, SerialException, SQLException
    {

        Pago pago = this.pagoServicio.buscarPagoPorId(id);
        if(pago == null){
            throw new RecursoNoEncontradoException("No se encontro el id: " + id);
        }

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        pago.setImgPago(blob);

        this.pagoServicio.guardarPago(pago);
        return ResponseEntity.ok(pago);

    }

    // display image
    @GetMapping("/img-pago/{id}")
    public ResponseEntity<byte[]> obtenerImagenPrenda(
            @PathVariable int id) throws IOException, SQLException
    {
        logger.info("Entramos al metodo par ala imagen: ");
        Pago pago = pagoServicio.buscarPagoPorId(id);
        byte [] imageBytes = null;

        imageBytes = pago.getImgPago().getBytes(1,(int) pago.getImgPago().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

}
