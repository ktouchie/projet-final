package co.simplon.reserve.repository;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.reserve.model.Reservation;

@Resource
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
