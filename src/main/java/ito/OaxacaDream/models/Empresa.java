package ito.OaxacaDream.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpresa;

    private String nombre;
    private String slogan;
    @Column(columnDefinition = "TEXT")
    private String vision;
    @Column(columnDefinition = "TEXT")
    private String mision;
    @Column(columnDefinition = "TEXT")
    private String valores;
    @Column(columnDefinition = "TEXT")
    private String trayectoria;
    @Column(columnDefinition = "TEXT")
    private String futuro;

}
