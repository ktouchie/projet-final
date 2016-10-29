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

    public Room() {

    }

    public Room(Integer id, String name, Integer capacity) {
	this.id = id;
	this.name = name;
	this.capacity = capacity;
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

}
