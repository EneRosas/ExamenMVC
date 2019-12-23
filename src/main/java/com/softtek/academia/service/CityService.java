package com.softtek.academia.service;

import java.util.List;

import com.softtek.academia.entity.City;

public interface CityService {

	public List<City> getAllCitys();

	public City getCitysById(Long id);

	public boolean saveCitys(City user);

	public boolean deleteCitysById(Long id);

}
