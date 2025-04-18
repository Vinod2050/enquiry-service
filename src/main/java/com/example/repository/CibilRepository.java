package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Cibil;

@Repository
public interface CibilRepository extends JpaRepository<Cibil, Integer> {

}
