/**
 * 
 */
package com.petstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author user
 *
 */

@Entity
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String color;
	
	private String petType;
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return petType;
	}

	public void setType(String type) {
		this.petType = type;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", color=" + color + ", type=" + petType + "]";
	}
	
	
	
	


}
