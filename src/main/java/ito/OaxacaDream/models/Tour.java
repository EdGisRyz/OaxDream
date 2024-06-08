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
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTour;
    private String nombre;
    private String descripcion;
    private Integer duracion; //horas
    private Double precio;
    private Integer capacidadMax;
    private Date fechaCreacion;
    private String localizacion;

    @Lob
    @JsonIgnore
    private Blob imgTour;


}
