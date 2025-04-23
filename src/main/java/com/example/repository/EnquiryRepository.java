package com.example.repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Enquiry;
import com.example.enums.EnquiryStatus;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {

	Page<Enquiry> findByFirstName(String firstName, Pageable pageable);

	Page<Enquiry> findByFirstNameAndEnquiryStatus(String firstName, EnquiryStatus enquiryStatus, Pageable pageable);

	Page<Enquiry> findByEnquiryStatus(EnquiryStatus enquiryStatus, Pageable pageable);

	List<Enquiry> findAllByEnquiryStatus(EnquiryStatus enquiryStatus);

}
