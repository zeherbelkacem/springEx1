package com.fms.springEx1.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author Stagiaires11P
 *
 */

@Entity
public class Article implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "BRAND")
	private String brand;

	@Column(name = "PRICE")
	private Double price;

//	@ManyToOne
//	@JoinColumn(name = "CATEGORY_ID")//It will have the same name by default
//	private Category category;

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Article [id=" + id + ", description=" + description + ", brand=" + brand + ", price=" + price + "]";
	}

	/**
	 * 
	 */
	public Article() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @param description
	 * @param brand
	 * @param price
	 */
	public Article(Long id, String description, String brand, Double price) {
		super();
		this.id = id;
		this.description = description;
		this.brand = brand;
		this.price = price;
	}

	/**
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * 
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * 
	 * @return
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}