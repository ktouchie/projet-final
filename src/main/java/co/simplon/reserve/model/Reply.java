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
	
	@ManyToOne(cascade = { CascadeType.MERGE }, targetEntity = Message.class)
    @JoinColumn(name = "messageId")
	private Message message;
	
	@ManyToOne(cascade = { CascadeType.MERGE }, targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;
	
	private String content;
	
	private Date replyDate;
	
	private boolean opened;
	
	
	public Reply() {
		
	}

	public Reply(Message message, User user, String content, Date replyDate, boolean opened) {
		super();
		this.message = message;
		this.user = user;
		this.content = content;
		this.replyDate = replyDate;
		this.opened = opened;
	}


	public boolean isOpened() {
		return opened;
	}


	public void setOpened(boolean opened) {
		this.opened = opened;
	}


	public Integer getId() {
		return id;
	}


	public Message getMessage() {
		return message;
	}

	public int getMessageId()
	{
		return message.getId();
	}

	public User getUser() {
		return user;
	}

	public Integer getUserId() {
		return user.getId();
	}
	
	public String getContent() {
		return content;
	}


	public Date getReplyDate() {
		return replyDate;
	}
	
	

}
