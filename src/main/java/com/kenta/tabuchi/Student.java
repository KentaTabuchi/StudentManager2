package com.kenta.tabuchi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="student")
public class Student {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private Long id;
	@NotNull
	@Column
	private String name;
	/**
	 * 
	 */
	@Column
	private String roma;
	@Column
	@Pattern(regexp="....[-]..[-]..")
	private String birthday;
	@Column
	@Pattern(regexp="...?.?[-]....?[-]....?")
	private String phone;
	@Column
	@Email
	private String email;
	@Column
	private String address;
	@Column
	@Range(min=1500,max=3000)
	private String graduation;
	
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGraduation() {
		return graduation;
	}
	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}
	public String getRoma() {
		return roma;
	}
	public void setRoma(String roma) {
		this.roma = roma;
	}




}
