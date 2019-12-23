package com.softtek.academia.service;

import java.util.List;

import com.softtek.academia.entity.States;

public interface StateService {
	public List<States> getAllStates();

	public States getStatesById(Long id);

	public boolean saveStates(States user);

	public boolean deleteStatesById(Long id);

}
