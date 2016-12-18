package co.simplon.reserve.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer capacity;

    private boolean enabled;

    public Room() {

    }

    public Room(String name, Integer capacity, boolean enabled) {
	this.name = name;
	this.capacity = capacity;
	this.enabled = enabled;
    }

    public Integer getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public Integer getCapacity() {
	return capacity;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public void updateRoomStatus(boolean enabled) {
	this.enabled = enabled;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setCapacity(Integer capacity) {
	this.capacity = capacity;
    }

}
