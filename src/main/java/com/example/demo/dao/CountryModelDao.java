package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.bean.CountryModel;

public interface CountryModelDao extends JpaRepository<CountryModel, Integer>{
	
	// jpa 会自动解析 findByCountryname countryname这个属性根据这个属性去数据库中查找
	CountryModel findByCountryname(String name);
	
	// jpa的分业不能直接写@Query语句
	@Query("select c from CountryModel c")
	List<CountryModel> findBySome(Pageable page);
	
	@Query("select c from CountryModel c where id =?")
	CountryModel findById(Integer id);
}
