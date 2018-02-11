package models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import constants.ForumMessageType;
import db_repositories.RepositoryUtils;
import forms.ForumForm;


@Entity
@Table(name = "forum")
public class Forum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@OneToOne()
	@JoinColumn(name = "fk_customer_id")
	private User user;

	@Column(name = "date")
	private Date date;
	
	@Column(name = "msg")
	private String msg;
	
	@Column(name = "type")
	private ForumMessageType type;
	
	@OneToOne()
	@JoinColumn(name = "fk_parent_id")
	private Forum forum;
	
	public Forum()
	{
		super();
	}
	public Forum(ForumForm forumForm)
	{
		this.msg = forumForm.getMsg();
		//this.date = new Date();
		this.date = java.util.Calendar.getInstance().getTime();  
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ForumMessageType getType() {
		return type;
	}

	public void setType(ForumMessageType type) {
		this.type = type;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
