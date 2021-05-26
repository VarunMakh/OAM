package com.g7.oam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g7.oam.entities.User;
import com.g7.oam.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	IUserRepository repository;

	@Override
	public List<User> showAllUsers() {
		// TODO Auto-generated method stub
		List<User> userlist = null;
		try {
			userlist = repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userlist;
	}

}
