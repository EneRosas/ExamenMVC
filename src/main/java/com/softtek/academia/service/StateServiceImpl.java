package com.softtek.academia.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.academia.entity.States;
import com.softtek.academia.repository.StateRepository;

@Service
@Transactional
public class StateServiceImpl implements StateService {

	// Implementing Constructor based DI
	private StateRepository repository;

	public StateServiceImpl() {

	}

	@Autowired
	public StateServiceImpl(StateRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<States> getAllStates() {
		List<States> list = new ArrayList<States>();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public States getStatesById(Long id) {
		States state = repository.findById(id).get();
		return state;
	}

	@Override
	public boolean saveStates(States state) {
		try {
			repository.save(state);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteStatesById(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
