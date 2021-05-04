package br.com.aijalon.medical.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aijalon.medical.dto.DoctorRequest;
import br.com.aijalon.medical.dto.DoctorResponse;
import br.com.aijalon.medical.dto.DoctorUpdateRequest;
import br.com.aijalon.medical.model.Doctor;
import br.com.aijalon.medical.model.Specialty;
import br.com.aijalon.medical.repository.SpecialtyRepository;

@Component
public class DoctorMappers {
	
	@Autowired 
	private SpecialtyRepository specialtyRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	 public DoctorResponse doctorToReponse(Doctor doctor) {
	    	return modelMapper.map(doctor, DoctorResponse.class);
	    }
	 
	 public Doctor requestToDTO(DoctorRequest doctorRequest) {
		 Doctor doctor = modelMapper.map(doctorRequest, Doctor.class);
		 doctor.setActive(true);
		 List<Specialty> specialty = this.specialtyRepository.findAllById(doctorRequest.getSpecialties());
		 doctor.setSpecialties(specialty);
		 return doctor;
	 }
	 
	 public void requestToModel(DoctorUpdateRequest doctorUpdateRequest, Doctor doctor) {
		 modelMapper.map(doctorUpdateRequest, doctor);
	 }

}
