package com.g7.oam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.g7.oam.entities.Admin;
import com.g7.oam.entities.User;
import com.g7.oam.exception.AdminNotFoundException;
import com.g7.oam.repository.IAdminRepository;

public class AdminServiceImpl implements IAdminService {

	@Autowired
	IAdminRepository repository;

	@Override
	public List<User> showAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin addAdmin(Admin admin) {
		try {
			repository.save(admin);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return admin;
	}

	@Override
	public Admin updateAdmin(Admin admin) throws AdminNotFoundException {
		try {
			repository.save(admin);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AdminNotFoundException("Admin not found for updation!");
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return admin;
	}

	@Override
	public Admin viewAdmin(Admin admin) throws AdminNotFoundException {
		try {
			repository.findById(admin.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AdminNotFoundException("Admin not found!");
		}
		// TODO Auto-generated method stub
		return admin;
	}

	@Override
	public Admin deleteAdmin(int adminId) throws AdminNotFoundException {
		Admin admin = new Admin();
		admin.setUserId(adminId);
		try {
			repository.deleteById(adminId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AdminNotFoundException("Admin not found for deletion!");
		}
		// TODO Auto-generated method stub
		return admin;
	}

	@Override
	public List<Admin> showAllAdmins() {
		List<Admin> adminList = null;
		try {
			adminList = repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return adminList;
	}

}
