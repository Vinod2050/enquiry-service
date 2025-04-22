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
import com.example.entity.Cibil;
import com.example.entity.Enquiry;
import com.example.enums.EnquiryStatus;
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
		Cibil cibil = new Cibil();
		enquiry.setEnquiryStatus(EnquiryStatus.REGISTERED);
		enquiry.setCibilScore(cibil);
		cibilRepository.save(cibil);
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
		return "Enquiry Not Found for id : " + enquiryId;
	}

	@Override
	public Page<EnquiryDTO> getAllEnquiries(String firstName,String enquiryStatus,int page, int size, String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy != null ? sortBy : "firstName","enquiryStatus"));

		if (firstName != null && enquiryStatus != null) {
			return enquiryRepository.findByFirstNameAndEmail(firstName, enquiryStatus, pageable)
					.map(entity -> modelMapper.map(entity, EnquiryDTO.class));
		} else if (firstName != null) {
			return enquiryRepository.findByFirstName(firstName, pageable)
					.map(entity -> modelMapper.map(entity, EnquiryDTO.class));
		} else if (enquiryStatus != null) {
			return enquiryRepository.findByEmail(enquiryStatus, pageable)
					.map(entity -> modelMapper.map(entity, EnquiryDTO.class));
		} else {
			return enquiryRepository.findAll(pageable).map(entity -> modelMapper.map(entity, EnquiryDTO.class));
		}
	}

	@Override
	public List<Enquiry> getEnquiriesByStatus(EnquiryStatus enquiryStatus) {
		return enquiryRepository.findAllByEnquiryStatus(enquiryStatus);
	}

	@Override
	public String putEnquiry(Integer enquiryId, EnquiryDTO enquiryDTO) {
		if (enquiryRepository.existsById(enquiryId)) {
			Enquiry getEnquiry = enquiryRepository.findById(enquiryId).get();
			Enquiry updatedEnquiry = modelMapper.map(enquiryDTO, Enquiry.class);
			updatedEnquiry.setEnquiryId(getEnquiry.getEnquiryId());
			enquiryRepository.save(updatedEnquiry);
			return "Enquiry details is updated...";
		}
		return "No enquiry found for equriry id : " + enquiryId;
	}

	

}
