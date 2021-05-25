package com.g7.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g7.oam.entities.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {

}
