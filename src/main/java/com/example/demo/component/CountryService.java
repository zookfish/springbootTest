package com.example.demo.component;

import com.example.demo.bean.CountryModel;

public interface CountryService {
	
	
	CountryModel select(Integer id);
	
	CountryModel update(CountryModel countryModel);
	
	String delete(Integer id);
}
