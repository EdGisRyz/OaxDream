package ito.OaxacaDream.repository;

import ito.OaxacaDream.models.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepositorio extends JpaRepository<Tour, Integer> {

}
