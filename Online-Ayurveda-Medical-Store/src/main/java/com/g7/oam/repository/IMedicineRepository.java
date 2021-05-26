package com.g7.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g7.oam.entities.Medicine;

@Repository
public interface IMedicineRepository extends JpaRepository<Medicine, String> {

}
