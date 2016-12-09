package co.simplon.reserve.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Subscriber {
	// JSR-303 API annotation
	@NotNull
    @Size(min=2, max=30)
    private String name;
    
	@NotEmpty
    @Size(min=2, max=30)
    private String surname;
    
    // Hibernate-Validator annotations
    @NotEmpty
    @Email
    private String email;
    
    @NotEmpty
    private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
	public String toString() {
        return "Subscriber(Name: " + this.name + ", Surname: " + this.surname
        		+ ", Email : " + this.email + ", Password : " + this.password + ")";
    }
    
}
