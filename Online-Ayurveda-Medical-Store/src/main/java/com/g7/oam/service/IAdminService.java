package com.g7.oam.service;

import java.util.List;

import com.g7.oam.entities.Admin;
import com.g7.oam.exception.AdminNotFoundException;

public interface IAdminService {

	public Admin addAdmin(Admin admin);

	public Admin updateAdmin(Admin admin) throws AdminNotFoundException;

	public Admin viewAdmin(Admin admin) throws AdminNotFoundException;

	public Admin deleteAdmin(int adminId) throws AdminNotFoundException;

	public List<Admin> showAllAdmins();
	
}
