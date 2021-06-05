package com.g7.oam.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g7.oam.entities.User;
import com.g7.oam.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
			logger.error(e.getMessage());
		}
		return userlist;
	}

}
