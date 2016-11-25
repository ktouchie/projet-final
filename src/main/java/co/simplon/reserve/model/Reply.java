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
public class Reply {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne(/*cascade = { CascadeType.ALL }, */targetEntity = Message.class)
    @JoinColumn(name = "messageId")
	private Message message;
	
	@ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;
	
	private String content;
	
	private Date replyDate;
	
	private boolean read;
	
	
	public Reply() {
		
	}

	public Reply(Message message, User user, String content, Date replyDate, boolean read) {
		super();
		this.message = message;
		this.user = user;
		this.content = content;
		this.replyDate = replyDate;
		this.read = read;
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


	public Message getMessage() {
		return message;
	}


	public User getUser() {
		return user;
	}


	public String getContent() {
		return content;
	}


	public Date getReplyDate() {
		return replyDate;
	}
	
	

}
