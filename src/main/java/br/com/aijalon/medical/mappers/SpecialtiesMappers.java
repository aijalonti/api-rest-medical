package br.com.aijalon.medical.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aijalon.medical.dto.SpecialtyDoctorResponse;
import br.com.aijalon.medical.dto.SpecialtyRequest;
import br.com.aijalon.medical.dto.SpecialtyResponse;
import br.com.aijalon.medical.model.Specialty;

@Component
public class SpecialtiesMappers {
		
	 @Autowired
	private ModelMapper modelMapper;
	 
	   public SpecialtyResponse specialtyToReponse(Specialty specialty) {
	    	return modelMapper.map(specialty, SpecialtyResponse.class);
	   }
	    
	   public SpecialtyDoctorResponse specialtyDoctorToReponse(Specialty specialty) {
		   return modelMapper.map(specialty, SpecialtyDoctorResponse.class);
	   }
	   
	   public Specialty requestToDTO(SpecialtyRequest specialtyRequest) {
	    	Specialty specialty = modelMapper.map(specialtyRequest, Specialty.class);
	    	specialty.setActive(true);
	    	return specialty;
	   }
	    	    
	   public void requestToModel(SpecialtyRequest specialtyRequest, Specialty specialy) {
	    	modelMapper.map(specialtyRequest, specialy);
	   }

}
