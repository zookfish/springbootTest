package com.example.demo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Country;
import com.example.demo.dao.CountryDao;

@Repository
public class CountryDaoImpl implements CountryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insert(Country country) {

		return jdbcTemplate.update("insert into country(countryname,countrycode) values (?,?)",
				country.getCountryName(), country.getCountryCode());
	}

}
