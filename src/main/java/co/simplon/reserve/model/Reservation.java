package co.simplon.reserve.model;

import java.util.Date;

import javax.persistence.CascadeType;
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

    private Date startTime;

    private Date endTime;

    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = Computer.class)
    @JoinColumn(name = "computerId")
    private Computer computer;

    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = Room.class)
    @JoinColumn(name = "roomId")
    private Room room;

    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;

    public Reservation() {

    }

    public Reservation(Date startTime, Date endTime, Computer computer, Room room, User user) {
	this.startTime = startTime;
	this.endTime = endTime;
	this.computer = computer;
	this.room = room;
	this.user = user;
    }

    public Integer getId() {
	return id;
    }

    public Date getStartTime() {
	return startTime;
    }

    public Date getEndTime() {
	return endTime;
    }

    public Computer getComputer() {
	return computer;
    }

    public Room getRoom() {
	return room;
    }

    public User getUser() {
	return user;
    }

}
