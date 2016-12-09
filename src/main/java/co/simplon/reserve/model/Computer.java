package co.simplon.reserve.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    private String serial;

    private boolean enabled;

    public Computer() {

    }

    public Computer(String brand, String serial, boolean enabled) {
	this.brand = brand;
	this.serial = serial;
	this.enabled = enabled;
    }

    public Integer getId() {
	return id;
    }

    public String getBrand() {
	return brand;
    }

    public String getSerial() {
	return serial;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public void updateComputerStatus(boolean enabled) {
	this.enabled = enabled;
    }

}
