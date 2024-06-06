package ito.OaxacaDream.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Servicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdUsuario;
    private String NombreServicio;
    private String Descripcion;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "UsuarioTour",
            joinColumns = @JoinColumn(name = "IdUsuario"),
            inverseJoinColumns = @JoinColumn(name = "IdTour"))
    private Set<Tour> tours = new HashSet<>();

}


