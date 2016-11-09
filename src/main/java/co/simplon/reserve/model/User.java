package co.simplon.reserve.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

    public enum Role {
	USER, ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;

    @OneToMany(mappedBy = "user")
    private Set<Reservation> reservations = new HashSet<Reservation>();

    public User() {
    }

    public User(String name, String surname, String email, String password, Role role, boolean enabled) {
	this.name = name;
	this.surname = surname;
	this.email = email;
	this.password = password;
	this.role = role;
	this.enabled = enabled;
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

    public String getPassword() {
	return password;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public Role getRole() {
	return role;
    }

}
