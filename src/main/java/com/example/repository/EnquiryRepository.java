package com.example.repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dto.EnquiryDTO;
import com.example.entity.Enquiry;
import com.example.enums.EnquiryStatus;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
	Page<EnquiryDTO>  findByFirstName(String firstName, Pageable pageable);
	Page<EnquiryDTO>  findByEmail(String email, Pageable pageable);
	Page<EnquiryDTO>  findByFirstNameAndEmail(String firstName, String email, Pageable pageable);
	List<Enquiry> findAllByStatus(EnquiryStatus status);
}


	