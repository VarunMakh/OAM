package com.g7.oam.service;

import java.util.List;

import com.g7.oam.entities.User;
import com.g7.oam.exception.InvalidLoginException;

public interface IUserService {

	public List<User> showAllUsers();
	
	public User login(User user) throws InvalidLoginException;
	
	public User logout();

}
