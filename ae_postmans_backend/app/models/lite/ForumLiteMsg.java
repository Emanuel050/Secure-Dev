package models.lite;

import java.io.Serializable;
import java.util.Date;

import constants.ForumMessageType;
import constants.PackageStatus;
import models.Forum;
import models.Package;

public class ForumLiteMsg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Date date;
	private String msg;
	private ForumMessageType type;
	private Long parentId;

	public ForumLiteMsg(Forum forum) {
		this.id = forum.getId();
		this.userId = forum.getUser().getId();
		this.date = forum.getDate();
		this.msg = forum.getMsg();
		this.type = forum.getType();
		if(this.type == type.ANSWER)
		{
			this.parentId = forum.getForum().getId();
		}
		else
		{
			this.parentId = 0L;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
