package br.com.aijalon.medical.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aijalon.medical.dto.DoctorResponse;
import br.com.aijalon.medical.dto.SpecialtyDoctorResponse;
import br.com.aijalon.medical.dto.SpecialtyRequest;
import br.com.aijalon.medical.dto.SpecialtyResponse;
import br.com.aijalon.medical.exception.ResourceNotFoundException;
import br.com.aijalon.medical.model.Doctor;
import br.com.aijalon.medical.model.Specialty;
import br.com.aijalon.medical.repository.SpecialtyRepository;

@Service
public class SpecialtyService {
    
    @Autowired
    private SpecialtyRepository specialtyRepository;
    
    @Autowired
	private ModelMapper modelMapper;

    public Specialty createSpecialty(SpecialtyRequest specialtyRequest) {
        Specialty specialty = new Specialty();
        specialty.setName(specialtyRequest.getName());
        specialty.setDescription(specialtyRequest.getDescription());
        specialty.setActive(true);

        return specialtyRepository.save(specialty);
    }

    public List<Specialty> find(){
        return specialtyRepository.findAll();
    }
    
    public List<SpecialtyResponse> findAll(){
    	return specialtyRepository.findAll()
    	       .stream()
    	       .map(this::specialtyToReponse)
    	       .collect(Collectors.toList());
    }
    
    public SpecialtyResponse findSpecialtyById(Long specialtyId){
    	Specialty specialty = specialtyRepository.findById(specialtyId)
               .orElseThrow(() -> new ResourceNotFoundException("Especialidade com Id:" + specialtyId + " não encontrado"));
		return specialtyToReponse(specialty);
    }
    
    public SpecialtyDoctorResponse findDoctorSpecialtyById(Long specialtyId){
    	Specialty specialty = specialtyRepository.findById(specialtyId)
               .orElseThrow(() -> new ResourceNotFoundException("Especialidade com Id:" + specialtyId + " não encontrado"));
		return specialtyDoctorToReponse(specialty);
    }

    public Specialty findById(Long id) {
        return specialtyRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Especialidade com Id:" + id + " não encontrado"));
    }
    
    public void updateSpecialty(Long id, SpecialtyRequest specialtyRequest) {
    	Specialty specialty = findById(id);
    	specialty.setName("Clinico Geral");
    	specialty.setDescription("Clinico faz consultas");
    	specialty.setActive(true);
    	specialtyRepository.save(specialty);
    }

    public void deleteSpecialty(Long id) {
       Specialty s = findById(id);
       specialtyRepository.save(s);
    }
    
    public SpecialtyResponse specialtyToReponse(Specialty specialty) {
    	return modelMapper.map(specialty, SpecialtyResponse.class);
    }
    
    public SpecialtyDoctorResponse specialtyDoctorToReponse(Specialty specialty) {
    	return modelMapper.map(specialty, SpecialtyDoctorResponse.class);
    }
    
    public void updateSpecialty2(SpecialtyRequest specialtyRequest, Specialty specialty) {
    	 modelMapper.map(specialtyRequest, specialty);
    }
   
}
