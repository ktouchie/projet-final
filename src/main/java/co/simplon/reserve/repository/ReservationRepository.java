package co.simplon.reserve.repository;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.reserve.model.Reservation;

@Resource
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    // list of Reservations for Computers which overlap with current reservation
    @Query("select r from Reservation r where r.computer.id = ?1 and not(r.endTime<=?2 or r.startTime>=?3)")
    public List<Reservation> computerConflicts(Integer computerId, Date startTime, Date endTime);

    // list of Reservations for Rooms which overlap with current reservation
    @Query("select r from Reservation r where r.room.id = ?1 and not(r.endTime<=?2 or r.startTime>=?3)")
    public List<Reservation> roomConflicts(Integer roomId, Date startTime, Date endTime);

    // list of Reservations given a User
    @Query("select r from Reservation r where r.user.id =?1")
    public List<Reservation> userReservations(Integer userId);
    
}
