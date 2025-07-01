package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Message {
    private int id;
    private int senderId;
    private int receiverId;
    private String content;
    private String sendTime;
    private String status; //已读/未读/删除等
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(Serializable timestamp) {
		this.sendTime = (String) timestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    

    // getter/setter
}