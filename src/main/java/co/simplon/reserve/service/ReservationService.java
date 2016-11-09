package co.simplon.reserve.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.reserve.model.Reservation;
import co.simplon.reserve.repository.ReservationRepository;

@Service
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

    public boolean computerAvailable(Integer computerId, Date startTime, Date endTime) {
	return reservationRepository.computerConflicts(computerId, startTime, endTime).isEmpty();
    }

    public boolean roomAvailable(Integer roomId, Date startTime, Date endTime) {
	return reservationRepository.roomConflicts(roomId, startTime, endTime).isEmpty();
    }
}
