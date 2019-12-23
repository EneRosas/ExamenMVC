package com.softtek.academia.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.academia.entity.City;
import com.softtek.academia.repository.CityRepository;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	// Implementing Constructor based DI
	private CityRepository repository;

	public CityServiceImpl() {

	}

	@Autowired
	public CityServiceImpl(CityRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<City> getAllCitys() {
		List<City> list = new ArrayList<City>();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public City getCitysById(Long id) {
		City city = repository.findById(id).get();
		return city;
	}

	@Override
	public boolean saveCitys(City city) {
		try {
			repository.save(city);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteCitysById(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
