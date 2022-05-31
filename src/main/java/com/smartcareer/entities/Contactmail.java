package com.smartcareer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contactmail")
public class Contactmail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cmid")
	private int cmid;
	
	@Column(name = "name")
	@NotBlank(message = "Name required")
	private String name;
	
	@Column(name = "email")
	@NotBlank(message = "Email required")
	private String email;
	
	@Column(name = "subject")
	@NotBlank(message = "Subject required")
	@Size(max = 100,message = "should not be more than 100 character")
	private String subject;
	
	@NotBlank(message = "Message required")
	@Column(name = "textmessage")
	private String textmessage;
	
	public int getCmid() {
		return cmid;
	}
	public void setCmid(int cmid) {
		this.cmid = cmid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTextmessage() {
		return textmessage;
	}
	public void setTextmessage(String textmessage) {
		this.textmessage = textmessage;
	}
	@Override
	public String toString() {
		return "Contactmail [cmid=" + cmid + ", name=" + name + ", email=" + email + ", subject=" + subject
				+ ", message=" + textmessage + "]";
	}

	
	
}
