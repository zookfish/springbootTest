package com.example.demo.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name = "country")
@NamedQuery(name = "CountryModel.findAll", query = "SELECT c FROM CountryModel c")
public class CountryModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
	private int id;

	@Column(length = 255)
	private String countrycode;

	@Column(length = 255)
	private String countryname;

	public CountryModel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountrycode() {
		return this.countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getCountryname() {
		return this.countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	@Override
	public String toString() {
		return "CountryModel [id=" + id + ", countrycode=" + countrycode + ", countryname=" + countryname + "]";
	}

}