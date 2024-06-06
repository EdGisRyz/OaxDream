package ito.OaxacaDream.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutenticacionUsuario extends Usuario {
    private Integer IdUsuario;
    private String CorreoElectronico;
    private String Contrasena;
}
