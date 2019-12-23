package com.softtek.academia.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.softtek.academia.entity.City;
import com.softtek.academia.entity.States;
import com.softtek.academia.service.CityService;
import com.softtek.academia.service.StateService;

@Controller
public class CityController {
	private CityService cityService;
	
	@Autowired
	private StateService stateService;

	public CityController() {
	}

	@Autowired
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

//	@RequestMapping(value = { "/", "/CitysCRUD" }, method = RequestMethod.GET)
//	public ModelAndView hello(HttpServletResponse response) throws IOException {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("CitysCRUD");
//		return mv;
//	}

	// GET ALL CITYS
	@RequestMapping(value = "/allCity", method = RequestMethod.POST)
	public ModelAndView displayAllCitys() {
		System.out.println("Citys Page Requested : All Citys");
		ModelAndView mv = new ModelAndView();
		List<City> cityList = cityService.getAllCitys();
		mv.addObject("cityList", cityList);
		mv.setViewName("allCity");
		return mv;
	}

	@RequestMapping(value = "/addCity", method = RequestMethod.GET)
	public ModelAndView displayNewCityForm() {
		ModelAndView mv = new ModelAndView("addCity");
		mv.addObject("headerMessage", "Add City Details");
		mv.addObject("city", new City());
		List<States> states = stateService.getAllStates();
		mv.addObject("states", states);
		return mv;
	}

	@RequestMapping(value = "/addCity", method = RequestMethod.POST)
	public ModelAndView saveNewCity(@ModelAttribute City city, BindingResult result) {
		ModelAndView mv = new ModelAndView("redirect:/home");

		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		boolean isAdded = cityService.saveCitys(city);
		if (isAdded) {
			mv.addObject("message", "New City Succesfully Added");
		} else {
			return new ModelAndView("error");
		}
		return mv;
	}

	@RequestMapping(value = "/editCity/{id}", method = RequestMethod.GET)
	public ModelAndView displayEditCityForm(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("/editCity");
		City city = cityService.getCitysById(id);
		mv.addObject("headerMessage", "Edit City Details");
		mv.addObject("city", city);
		return mv;
	}

	@RequestMapping(value = "/editCity/{id}", method = RequestMethod.POST)
	public ModelAndView saveEditedCity(@ModelAttribute City city, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			System.out.println(result.toString());
			return new ModelAndView("error");
		}
		boolean isSaved = cityService.saveCitys(city);
		if (!isSaved) {
			return new ModelAndView("error");
		}
		return mv;
	}

	@RequestMapping(value = "/deleteCity/{id}", method = RequestMethod.GET)
	public ModelAndView deleteCityById(@PathVariable Long id) {
		boolean isDeleted = cityService.deleteCitysById(id);
		System.out.println("User deletion response: " + isDeleted);
		ModelAndView mv = new ModelAndView("redirect:/home");
		return mv;
	}
	
//	protected Map referenceData(HttpServletRequest request) throws Exception {
//		Map referenceData = new HashMap();
//		
//		Map<Long,String> stateList = new LinkedHashMap<Long,String>();
//		
//		List<States> states = stateService.getAllStates();
//		
//		for(States s: states) {
//			stateList.put(s.getState_id(), s.getDescription());
//		}
//		
//		//referenceData.put("stateList", stateList)
////		states.put(key, value)
//		return referenceData;
//	}
}
