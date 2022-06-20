package com.fms.springEx1.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@NotNull(message = "Can't be null!")
	@Size(min = 10, max = 50)
	private String description;

	@Column(name = "BRAND")
	private String brand;

	@Column(name = "PRICE")
	@DecimalMin("10")
	private Double price;

	@Column(name = "QUANTITY")
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID") // It will have the same name by default
	private Category category;

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Article [id=" + id + ", description=" + description + ", brand=" + brand + ", price=" + price
				+ ", category=" + category.getName() + "]";
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	 * @param category
	 */
	public Article(Long id, String description, String brand, Double price, Category category) {
		super();
		this.id = id;
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.category = category;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * 
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}