package com.fms.springEx1.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fms.springEx1.Security.Uuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	@NotNull(message = "Can't be null!")
	@Size(min = 4, max = 25)
	private String firstName;
	
	@Column
	@NotNull(message = "Can't be null!")
	@Size(min = 4, max = 25)
	private String lastName;
	
	@Column
	@Email(regexp = ".+[@].+[\\.].+", message = "Please enter à valid mail")
	private String email;
	
	@Column(unique = true)
	@Pattern(regexp = "^[0-9]{10}$", message = "Your number phone format is incorrect") //0000000000
	private String phone;
	
	@Column
	private String address;
	
	@ManyToOne
	private Uuser uuser;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Order> orders;
	
}
