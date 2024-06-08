package ito.OaxacaDream.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AutenticacionUsuario extends Usuario {
    private Integer IdUsuario;
    private String CorreoElectronico;
    private String Contrasena;
}
