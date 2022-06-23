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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fms.springEx1.Entities.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "USERS")
public class Uuser {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@NotNull
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "EMAIL")
	@Email(regexp = ".+[@].+[\\.].+", message = "Please enter à valid mail")
	private String email;
	
	@NotNull
	private String password;
	
	private Boolean active;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Rrole> roles = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orders;
	
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + userName + ", email=" + email + ", password=" + password + ", enable=" + active
				+"]";
	}



	public Uuser(long userId, @NotNull String userName,
			@Email(regexp = ".+[@].+[\\.].+", message = "Please enter à valid mail") String email,
			@NotNull String password, Boolean active) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.active = active;
	}



	

}