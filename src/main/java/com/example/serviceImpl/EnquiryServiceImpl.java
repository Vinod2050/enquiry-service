package com.example.serviceImpl;

import java.util.Optional;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.dto.EnquiryDTO;
import com.example.entity.Enquiry;
import com.example.enums.EnquiryStatus;
import com.example.repository.EnquiryRepository;
import com.example.service.EnquiryService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired
	private EnquiryRepository enquiryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String saveEnquiry(EnquiryDTO dto) {
		try {
			System.out.println(dto);
			Enquiry enquiry = modelMapper.map(dto, Enquiry.class);

			System.out.println(enquiry);

			
			enquiry.setIsDeleted(false);
			// enquiry.setMobileNo(Long.parseLong(dto.getMobileNo()));

			enquiry.setIsDeleted(false);
			//enquiry.setMobileNo(Long.parseLong(dto.getMobileNo()));

			// enquiry.setMobileNo(Long.parseLong(dto.getMobileNo()));

			enquiryRepository.save(enquiry);
			return "Enquiry Submited Successfully";
		} catch (NumberFormatException e) {
			return "Invalid Mobile Number Format";
		}
	}
		
	@Override
	public String saveStatus(Integer enquiryId, String status) {
		 Optional<Enquiry> opt = enquiryRepository.findById(enquiryId);
		    if (opt.isPresent()) {
		        Enquiry enquiry = opt.get();
		        enquiry.setEnquiryStatus(status);  
		        enquiryRepository.save(enquiry);
		        return "Enquiry status updated successfully.";
		    } else {
		        return "Enquiry not found.";
		    }
	}

	@Override

	public EnquiryDTO getEnquiry(Integer enquiryID) {
		if (enquiryRepository.existsById(enquiryID)) {
			
			Enquiry enquiry = enquiryRepository.findById(enquiryID).get();
			EnquiryDTO enquirydto = modelMapper.map(enquiry, EnquiryDTO.class);

			return enquirydto;
		}
		return null;
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
		return "Enquiry Id Not Found....!";
	}

	public Page<EnquiryDTO>  getAllEnquiries(String firstName, String email, int page, int size, String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy != null ? sortBy : "firstName"));

		if (firstName != null && email != null) {
			return enquiryRepository.findByFirstNameAndEmail(firstName, email, pageable)
					.map(entity -> modelMapper.map(entity, EnquiryDTO.class));
		} else if (firstName != null) {
			return enquiryRepository.findByFirstName(firstName, pageable)
					.map(entity -> modelMapper.map(entity, EnquiryDTO.class));
		} else if (email != null) {
			return enquiryRepository.findByEmail(email, pageable)
					.map(entity -> modelMapper.map(entity, EnquiryDTO.class));
		} else {
			return enquiryRepository.findAll(pageable).map(entity -> modelMapper.map(entity, EnquiryDTO.class));
		}
	}
	
	@Override
	public List<Enquiry> getEnquiriesById(EnquiryStatus status) {
		
		return enquiryRepository.findAllByStatus(status);
	}

}
