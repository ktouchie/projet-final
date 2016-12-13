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

    private boolean delByUser;

    private boolean delByAdmin;

    public Message() {

    }

    public Message(User user, String title, String content, Date creationDate) {
	this.user = user;
	this.title = title;
	this.content = content;
	this.creationDate = creationDate;
	this.opened = false;
	this.delByUser = false;
	this.delByAdmin = false;
    }

    public Integer getId() {
	return id;
    }

    public User getUser() {
	return user;
    }

    public Integer getUserId() {
	return user.getId();
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

    public boolean isOpened() {
	return opened;
    }

    public boolean isDelByUser() {
	return delByUser;
    }

    public boolean isDelByAdmin() {
	return delByAdmin;
    }

    public void setOpened(boolean opened) {
	this.opened = opened;
    }

    public void setDelByUser(boolean delByUser) {
	this.delByUser = delByUser;
    }

    public void setDelByAdmin(boolean delByAdmin) {
	this.delByAdmin = delByAdmin;
    }

}
