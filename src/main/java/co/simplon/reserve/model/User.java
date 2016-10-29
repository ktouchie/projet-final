package co.simplon.reserve.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private String email;

    public User() {
	// FOR HIBERNATE
    }

    public User(String name, String surname, String email) {
	this.name = name;
	this.surname = surname;
	this.email = email;
    }

    public Integer getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public String getSurname() {
	return surname;
    }

    public String getEmail() {
	return email;
    }

}
