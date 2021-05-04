package br.com.aijalon.medical.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aijalon.medical.dto.SpecialtyDoctorResponse;
import br.com.aijalon.medical.dto.SpecialtyRequest;
import br.com.aijalon.medical.dto.SpecialtyResponse;
import br.com.aijalon.medical.exception.ResourceBadRequestException;
import br.com.aijalon.medical.exception.ResourceNotFoundException;
import br.com.aijalon.medical.mappers.SpecialtiesMappers;
import br.com.aijalon.medical.model.Specialty;
import br.com.aijalon.medical.repository.SpecialtyRepository;

@Service
public class SpecialtyService {
    
   @Autowired
   private SpecialtyRepository specialtyRepository;
    
   @Autowired
   private SpecialtiesMappers specialitieMappers;

    public Specialty createSpecialty(SpecialtyRequest specialtyRequest) {
        Specialty specialty = specialitieMappers.requestToDTO(specialtyRequest);
        return specialtyRepository.save(specialty);
    }
    
    public List<SpecialtyResponse> getAll(){
    	return specialtyRepository.findAll()
    	       .stream()
    	       .map(s -> specialitieMappers.specialtyToReponse(s))
    	       .collect(Collectors.toList());
    }
    
    public SpecialtyResponse getSpecialtyById(Long specialtyId){
    	Specialty specialty = specialtyRepository.findById(specialtyId)
               .orElseThrow(() -> new ResourceNotFoundException("Especialidade com Id:" + specialtyId + " não encontrado"));
		return specialitieMappers.specialtyToReponse(specialty);
    }
    
    public SpecialtyDoctorResponse getDoctorSpecialtyById(Long specialtyId){
    	Specialty specialty = specialtyRepository.findById(specialtyId)
               .orElseThrow(() -> new ResourceNotFoundException("Especialidade com Id:" + specialtyId + " não encontrado"));
		return specialitieMappers.specialtyDoctorToReponse(specialty);
    }

    public Specialty findById(Long id) {
        return specialtyRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Especialidade com Id:" + id + " não encontrado"));
    }
    
    public void updateSpecialty(Long id, SpecialtyRequest specialtyRequest) {
    	Specialty specialty = findById(id);
    	specialitieMappers.requestToModel(specialtyRequest, specialty);
    	specialtyRepository.save(specialty);
    }

    public void deleteSpecialty(Long id) {
       Specialty specialty = findById(id);
       if(specialty.getDoctor().isEmpty()) {
       specialtyRepository.delete(specialty);
       
       }else {
    	   throw new ResourceBadRequestException("Não é possivel excluir. Existem médicos relacionados a especialidades!");
       }
    }     
}
