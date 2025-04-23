package com.example.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Cibil;
import com.example.entity.Enquiry;
import com.example.repository.CibilRepository;
import com.example.repository.EnquiryRepository;
import com.example.service.CibilService;

@Service
public class CibilServiceImpl implements CibilService {
	@Autowired
	private EnquiryRepository enquiryRepository;

	@Autowired
	private CibilRepository cibilRepository;

	@Autowired
	public ModelMapper modelMapper;

	@Override
	public String generateCibil(Integer enquiryId) {

		if (enquiryRepository.existsById(enquiryId)) {

			Enquiry enquiry = enquiryRepository.findById(enquiryId).get();

			Cibil generatedCibilDetails = CibilCalculator.evaluateCibilScore(enquiry);

			generatedCibilDetails.setEnquiryId(enquiryId);

			cibilRepository.save(generatedCibilDetails);

			enquiry.setCibilScore(generatedCibilDetails);

			enquiryRepository.save(enquiry);

			return "cibil details saved and checked successfully";
		}
		return "no cibil details/enquiry found for enquiry id : " + enquiryId;
	}

//	@Override
//	public String updateCibilDetails(Integer enquiryId, CibilDTO cibilDto) {
//
//		if (enquiryRepository.existsById(enquiryId)) {
//
//			Enquiry enquiry = enquiryRepository.findById(enquiryId).get();
//
//			Cibil cibilScore = enquiry.getCibilScore();
//
//			if (cibilDto.getCibilScore() != null) {
//				cibilScore.setCibilScore(cibilDto.getCibilScore());
//			}
//			if (cibilDto.getStatus() != null) {
//				cibilScore.setStatus(cibilDto.getStatus());
//			}
//			if (cibilDto.getCibilRemark() != null) {
//				cibilScore.setCibilRemark(cibilDto.getCibilRemark());
//			}
//			enquiry.setCibilScore(cibilScore);
//			enquiryRepository.save(enquiry);
//			return "Given cibil details updated succesfully..!";
//		}
//		return "no cibil details/enquiry found for updating given enquiry id : " + enquiryId;
//	}
}
