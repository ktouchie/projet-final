package co.simplon.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.simplon.reserve.model.Reservation;
import co.simplon.reserve.repository.ReservationRepository;

@Repository
public class ReservationService {

    @Autowired
    public ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
	return reservationRepository.findAll();
    }

    public void delete(Integer id) {
	reservationRepository.delete(id);
    }

    public Reservation add(Reservation reservation) {
	return reservationRepository.save(reservation);
    }

    public Reservation getById(Integer id) {
	return reservationRepository.findOne(id);
    }

}
