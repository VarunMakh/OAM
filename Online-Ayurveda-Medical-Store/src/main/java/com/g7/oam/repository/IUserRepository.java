package com.g7.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g7.oam.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

}
