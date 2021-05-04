package br.com.aijalon.medical.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aijalon.medical.dto.DoctorRequest;
import br.com.aijalon.medical.dto.DoctorResponse;
import br.com.aijalon.medical.dto.DoctorUpdateRequest;
import br.com.aijalon.medical.exception.ResourceNotFoundException;
import br.com.aijalon.medical.mappers.DoctorMappers;
import br.com.aijalon.medical.model.Doctor;
import br.com.aijalon.medical.model.Specialty;
import br.com.aijalon.medical.repository.DoctorRepository;
import br.com.aijalon.medical.repository.SpecialtyRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorMappers doctorMappers;
	
	@Autowired
	private SpecialtyRepository specialtyRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
    
    public Doctor createDoctor(DoctorRequest doctorRequest) {
    	Doctor doctor = doctorMappers.requestToDTO(doctorRequest);
    	return doctorRepository.save(doctor);
    }

    public List<DoctorResponse> getAll(){
        return doctorRepository.findAll()
        		.stream()
        		.map(s -> doctorMappers.doctorToReponse(s))
        		.collect(Collectors.toList());
        
    }

    public DoctorResponse getDoctorById(Long doctorId){
    	Doctor doctor = doctorRepository.findById(doctorId)
               .orElseThrow(() -> new ResourceNotFoundException("Médico com Id:" + doctorId + " não encontrado"));
		return doctorMappers.doctorToReponse(doctor);
    }
    
    public Doctor findById(Long id){
        return doctorRepository.findById(id)
                               .orElseThrow(() -> new ResourceNotFoundException("Médico com Id: " + id + " não encontrado"));
    }
    
    public void updateDoctor(Long id, DoctorUpdateRequest doctorUpdateRequest) {
    	Doctor doctor = findById(id);
    	doctorMappers.requestToModel(doctorUpdateRequest, doctor);
    	List<Specialty> specialty = this.specialtyRepository.findAllById(doctorUpdateRequest.getSpecialties());
    	doctor.setSpecialties(specialty);
    	doctorRepository.save(doctor);
    }
    
    public void deleteDoctor(Long id){
        Optional<Doctor> optionalDoctor = Optional.of(this.findById(id));
        optionalDoctor.ifPresent((deleteDoctor) -> doctorRepository.delete(deleteDoctor));
    }
}


