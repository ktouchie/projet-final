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
	
	private String title;
	
	private String content;
	
	//private Date creationDate;
	
	private boolean read;
	
	@ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId") 
    private User user;

	public Message() {
		
	}

	public Message(String title, String content,/*Date creationDate,*/ boolean read, User user) {
		this.title = title;
		this.content = content;
//		this.creationDate = creationDate;
		this.read = read;
		this.user = user;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
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

//	public Date getCreationDate() {
//		return creationDate;
//	}
	
	
	
	
}
