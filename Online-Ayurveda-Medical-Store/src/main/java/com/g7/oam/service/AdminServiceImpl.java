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
		try {
			optional = repository.findById(admin.getUserId());
			repository.save(admin);
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new AdminNotFoundException("Admin not found for updation!");
			}
		}
		return optional.get();
	}

	@Override
	public Admin viewAdmin(Admin admin) throws AdminNotFoundException {
		Optional<Admin> optional = null;
		try {
			optional = repository.findById(admin.getUserId());
			repository.findById(admin.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new AdminNotFoundException("Admin not found!");
			}
		}
		return optional.get();
	}

	@Override
	@Transactional
	public Admin deleteAdmin(int adminId) throws AdminNotFoundException {
		Optional<Admin> optional = null;
		try {
			optional = repository.findById(adminId);
			repository.deleteById(adminId);
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new AdminNotFoundException("Admin not found for deletion!");
			}
		}
		return optional.get();
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
