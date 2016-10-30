package co.simplon.reserve.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer idComputer;

    private Integer idRoom;

    private Integer idUser;

    public Reservation() {

    }

    public Reservation(Integer idComputer, Integer idUser, LocalDateTime startTime, LocalDateTime endTime) {
	this.startTime = startTime;
	this.endTime = endTime;
	this.idComputer = idComputer;
	this.idUser = idUser;
    }

    public Reservation(LocalDateTime startTime, LocalDateTime endTime, Integer idRoom, Integer idUser) {
	this.startTime = startTime;
	this.endTime = endTime;
	this.idRoom = idRoom;
	this.idUser = idUser;
    }

    public Reservation(LocalDateTime startTime, LocalDateTime endTime, Integer idComputer, Integer idRoom,
	    Integer idUser) {
	this.startTime = startTime;
	this.endTime = endTime;
	this.idComputer = idComputer;
	this.idRoom = idRoom;
	this.idUser = idUser;
    }

}
