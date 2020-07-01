package spring.boot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "imageUser")
public class ImageUser {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMG_SEQ")
    @SequenceGenerator(name = "IMG_SEQ", sequenceName = "IMG_SEQ", allocationSize = 1)
	private long id;
	
	//@NotNull(message = "Image name is required.")
	@Column(name = "name")
	private String name;
	
	//@NotNull(message = "Image Type is required.")
	@Column(name = "type")
	private String type;
	
	
	@Lob
	@Column(name = "picByte")
	private byte[] picByte;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "User_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
	

	
	
	
	
	
	
	
	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public ImageUser(String name, String type, byte[] picByte, User user) {
		super();
		this.name = name;
		this.type = type;
		this.picByte = picByte;
		this.user = user;
	}




	public ImageUser() {
	
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public byte[] getPicByte() {
		return picByte;
	}


	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}


	public ImageUser(long id, String name, String type, byte[] picByte) {
	
		this.id = id;
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}


	public ImageUser( String name,String type, byte[] picByte) {
	
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}



	
	
	
	
}
