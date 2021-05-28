package com.g7.oam.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g7.oam.entities.Admin;
import com.g7.oam.exception.AdminNotFoundException;
import com.g7.oam.repository.IAdminRepository;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	IAdminRepository repository;

	@Override
	@Transactional
	public Admin addAdmin(Admin admin) {
		try {
			repository.save(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	@Transactional
	public Admin updateAdmin(Admin admin) throws AdminNotFoundException {
		Optional<Admin> optional = null;
		optional = repository.findById(admin.getUserId());
		if (optional.isPresent()) {
			repository.save(admin);
			return optional.get();
		} else {

			throw new AdminNotFoundException("Admin not found for updation!");
		}
	}

	@Override
	public Admin viewAdmin(Admin admin) throws AdminNotFoundException {
		Optional<Admin> optional = null;
		optional = repository.findById(admin.getUserId());
		if (optional.isPresent()) {
			return optional.get();
		} else {

			throw new AdminNotFoundException("Admin not found!");
		}
	}

	@Override
	@Transactional
	public Admin deleteAdmin(int adminId) throws AdminNotFoundException {
		Optional<Admin> optional = null;
		optional = repository.findById(adminId);
		if (optional.isPresent()) {
			repository.deleteById(adminId);
			return optional.get();
		} else {

			throw new AdminNotFoundException("Admin not found for deletion!");
		}
	}

	@Override
	public List<Admin> showAllAdmins() {
		List<Admin> adminList = null;
		try {
			adminList = repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminList;
	}

}
