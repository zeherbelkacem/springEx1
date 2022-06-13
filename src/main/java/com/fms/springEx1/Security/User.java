package com.fms.springEx1.Security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@Column(name = "EMAIL")
	private String email;
	
	private String password;
	
	private Boolean enable;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Role> roles = new ArrayList<>();

//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	private List<Order> orders;
	
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", enable=" + enable
				+"]";
	}



	

}