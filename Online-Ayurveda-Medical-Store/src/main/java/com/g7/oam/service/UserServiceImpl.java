package com.g7.oam.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g7.oam.entities.User;
import com.g7.oam.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

	@Autowired
	IUserRepository repository;

	public UserServiceImpl(IUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<User> showAllUsers() {

		List<User> userlist = null;
		try {
			userlist = repository.findAll();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return userlist;
	}

}
