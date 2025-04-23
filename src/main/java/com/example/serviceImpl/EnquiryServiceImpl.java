package com.example.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.dto.EnquiryDTO;
import com.example.entity.Enquiry;
import com.example.enums.EnquiryStatus;
import com.example.exception.EnquiryIdNotFound;
import com.example.repository.CibilRepository;
import com.example.repository.EnquiryRepository;
import com.example.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	


	@Autowired
	private EnquiryRepository enquiryRepository;

	@Autowired
	private CibilRepository cibilRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String saveEnquiry(EnquiryDTO dto) {
		Enquiry enquiry = modelMapper.map(dto, Enquiry.class);
		enquiry.setIsDeleted(false);
		enquiry.setMobileNo(Long.parseLong(dto.getMobileNo()));
		enquiry.setEnquiryStatus(EnquiryStatus.REGISTERED);
		enquiryRepository.save(enquiry);
		return "Enquiry Submited Successfully";
	}

	@Override
	public String saveStatus(Integer enquiryId, EnquiryStatus enquiryStatus) {
		Optional<Enquiry> opt = enquiryRepository.findById(enquiryId);
		if (opt.isPresent()) {
			Enquiry enquiry = opt.get();
			
			enquiry.setEnquiryStatus(enquiryStatus);
			
			
			
			enquiryRepository.save(enquiry);
			return "Enquiry status updated successfully.";
		} else {
			return "Enquiry not found/existed..";
		}
	}

	@Override
	public EnquiryDTO getEnquiry(Integer enquiryID) {
	    Enquiry enquiry = enquiryRepository.findById(enquiryID)
	            .orElseThrow(() -> new EnquiryIdNotFound("Enquiry not found for ID: " + enquiryID));
	    return modelMapper.map(enquiry, EnquiryDTO.class);
	}


	@Override
	public String deleteEnquriy(Integer enquiryId) {
		Optional<Enquiry> enquiry = enquiryRepository.findById(enquiryId);
		if (enquiry.isPresent()) {
			Enquiry getenquiry = enquiry.get();
			getenquiry.setIsDeleted(true);
			enquiryRepository.save(getenquiry);
			return "Enquiry Deleted Successfully...!";
		}
		return "Enquiry Not Found for id : " + enquiryId;
	}

	@Override
	public Page<EnquiryDTO> getAllEnquiries(String firstName, EnquiryStatus enquiryStatus, int page, int size, String sortBy) {
	    // Handle multiple sort fields, e.g., "firstName,enquiryStatus"
	    String[] sortFields = (sortBy != null && !sortBy.isBlank()) ? sortBy.split(",") : new String[]{"firstName"};
	    Sort sort = Sort.by(sortFields);
	    Pageable pageable = PageRequest.of(page, size, sort);

	    Page<Enquiry> enquiryPage;

	    if (firstName != null && enquiryStatus != null) {
	        enquiryPage = enquiryRepository.findByFirstNameAndEnquiryStatus(firstName, enquiryStatus, pageable);
	    } else if (firstName != null) {
	        enquiryPage = enquiryRepository.findByFirstName(firstName, pageable);
	    } else if (enquiryStatus != null) {
	        enquiryPage = enquiryRepository.findByEnquiryStatus(enquiryStatus, pageable);
	    } else {
	        enquiryPage = enquiryRepository.findAll(pageable);
	    }

	    return enquiryPage.map(enquiry -> modelMapper.map(enquiry, EnquiryDTO.class));
	}


	@Override
	public List<Enquiry> getEnquiriesByStatus(EnquiryStatus enquiryStatus) {
		return enquiryRepository.findAllByEnquiryStatus(enquiryStatus);
	}
	@Override
	public String putEnquiry(Integer enquiryId, EnquiryDTO enquiryDTO) {
	    Enquiry existingEnquiry = enquiryRepository.findById(enquiryId)
	            .orElseThrow(() -> new EnquiryIdNotFound("Enquiry not found for ID: " + enquiryId));
	    
	    Enquiry updatedEnquiry = modelMapper.map(enquiryDTO, Enquiry.class);
	    updatedEnquiry.setEnquiryId(existingEnquiry.getEnquiryId());
	    
	    enquiryRepository.save(updatedEnquiry);
	    
	    return "Enquiry details updated successfully.";
	}


	

}
