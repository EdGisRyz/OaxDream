package ito.OaxacaDream.repository;

import ito.OaxacaDream.models.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRepositorio extends JpaRepository<MetodoPago, Integer> {
}
