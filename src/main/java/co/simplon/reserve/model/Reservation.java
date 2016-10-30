package co.simplon.reserve.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
    
    @ManyToOne
    @JoinColumn(name = "computer_id")
    private Computer computer;
    
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Reservation() {

    }

    public Reservation(LocalDateTime startTime, LocalDateTime endTime, Computer computer, User user) {
	this.startTime = startTime;
	this.endTime = endTime;
	this.computer = computer;
	this.user = user;
    }

    public Reservation(LocalDateTime startTime, LocalDateTime endTime, Room room, User user) {
	this.startTime = startTime;
	this.endTime = endTime;
	this.room = room;
	this.user = user;
    }

    public Reservation(LocalDateTime startTime, LocalDateTime endTime, Computer computer, Room room, User user) {
	this.startTime = startTime;
	this.endTime = endTime;
	this.computer = computer;
	this.room = room;
	this.user = user;
    }

}
