package com.project.model;

import java.util.Arrays;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "image_gallery")
public class ImageGallery {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "fathername", nullable = false)
	private String fathername;
	
	@Column(name = "dob", nullable = false)
	private String dob;
	
	@Column(name = "occupation", nullable = false)
	private String occupation;

	@Column(name = "village", nullable = false)
	private String village;	

	
	@Column(name = "post", nullable = false)
	private String post;
	
	
	@Column(name = "dist", nullable = false)
	private String dist;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "pin", nullable = false)
	private String pin;
	
/*	@Column(name = "price",nullable = false, precision = 10, scale = 2)
    private double price;
	*/
	
	
	
	@Column(name = "state", nullable = false)
	private String state;
	
	@Column(name = "contact", nullable = false)
	private String contact;
	
	@Column(name = "gender", nullable = true)
	private String gender;
	
	@Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;

	public ImageGallery() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "ImageGallery [id=" + id + ", name=" + name + ", fathername=" + fathername + ", dob=" + dob
				+ ", occupation=" + occupation + ", village=" + village + ", post=" + post + ", dist=" + dist
				+ ", city=" + city + ", pin=" + pin + ", state=" + state + ", contact=" + contact + ", gender=" + gender
				+ ", image=" + Arrays.toString(image) + ", createDate=" + createDate + "]";
	}

	

   
}


