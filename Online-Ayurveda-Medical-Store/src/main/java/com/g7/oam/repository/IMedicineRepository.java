package com.g7.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g7.oam.entities.Medicine;

public interface IMedicineRepository extends JpaRepository<Medicine, Integer> {

}
