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
public class Message {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne(cascade = { CascadeType.MERGE }, targetEntity = User.class)
    @JoinColumn(name = "userId") 
    private User user;
	
	private String title;
	
	private String content;
	
	private Date creationDate;
	
	private boolean opened;
	

	public Message() {
		
	}

	public Message(User user, String title, String content, Date creationDate, boolean opened) {
		this.user = user;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.opened = opened;
	}

	public boolean isOPened() {
		return opened;
	}

	public void setRead(boolean opened) {
		this.opened = opened;
	}

	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Date getCreationDate() {
		return creationDate;
	}
		
}
