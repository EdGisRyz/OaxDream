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
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_reserva", referencedColumnName = "idReserva")
    private Reserva reserva;

    private MetodoPago metodoPago;
    private Double monto;
    private Date fechaPago;
    private String estado; //pagado - pendiente

    @Lob
    @JsonIgnore
    private Blob imgPago;

}
