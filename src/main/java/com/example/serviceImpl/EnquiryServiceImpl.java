package com.example.serviceImpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.EnquiryDTO;
import com.example.entity.Enquiry;
import com.example.repository.EnquiryRepository;
import com.example.service.EnquiryService;

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
			//enquiry.setMobileNo(Long.parseLong(dto.getMobileNo()));
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

}
