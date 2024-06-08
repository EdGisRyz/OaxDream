package ito.OaxacaDream.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String email;
    private String contrasena;
    private String telefono;
    private String tipo; //admin o cliente
    private Date fechaRegistro;
    private String direccion;

    @Lob
    @JsonIgnore
    private Blob imgUsuario;

}
