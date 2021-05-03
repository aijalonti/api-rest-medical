package br.com.aijalon.medical.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aijalon.medical.dto.DoctorRequest;
import br.com.aijalon.medical.dto.DoctorResponse;
import br.com.aijalon.medical.exception.ResourceNotFoundException;
import br.com.aijalon.medical.model.Doctor;
import br.com.aijalon.medical.model.Specialty;
import br.com.aijalon.medical.repository.DoctorRepository;
import br.com.aijalon.medical.repository.SpecialtyRepository;

@Service
public class DoctorService {
	
	@Autowired
	private ModelMapper modelMapper;

	private DoctorRepository doctorRepository;
	
	private final SpecialtyRepository specialtyRepository;
    
    public DoctorService(DoctorRepository doctorRepository, SpecialtyRepository specialtyRepository) {
		this.doctorRepository = doctorRepository;
		this.specialtyRepository = specialtyRepository;
	}


    public Doctor createDoctor(DoctorRequest doctorRequest) {
    	Doctor doctor = new Doctor();
    	doctor.setName(doctorRequest.getName());
    	doctor.setBirthDate(doctorRequest.getBirthDate());
    	doctor.setActive(true);
    	List<Specialty> specialty = this.specialtyRepository.findAllById(doctorRequest.getSpecialties());
    	doctor.setSpecialties(specialty);

        return doctorRepository.save(doctor);
    }

    public List<DoctorResponse> findAll(){
        return doctorRepository.findAll()
        		.stream()
        		.map(this::doctorToReponse)
        		.collect(Collectors.toList());
        
    }

    public DoctorResponse findDoctorById(Long doctorId){
    	Doctor doctor = doctorRepository.findById(doctorId)
               .orElseThrow(() -> new ResourceNotFoundException("Médico com Id:" + doctorId + " não encontrado"));
		return doctorToReponse(doctor);
    }
    
    public Doctor findById(Long id){
        return doctorRepository.findById(id)
                               .orElseThrow(() -> new ResourceNotFoundException("Médico com Id: " + id + " não encontrado"));
    }
    
    public void updateDoctor(Long id, DoctorRequest doctorRequest) {
    	Doctor doctor = findById(id);
    	doctor.setName(doctorRequest.getName());
    	doctor.setBirthDate(doctorRequest.getBirthDate());
    	doctor.setActive(doctorRequest.getActive());
    	List<Specialty> specialty = this.specialtyRepository.findAllById(doctorRequest.getSpecialties());
    	doctor.setSpecialties(specialty);
    	doctorRepository.save(doctor);
    }
    
    public void deleteDoctor(Long id){
        Optional<Doctor> optionalDoctor = Optional.of(this.findById(id));
        optionalDoctor.ifPresent((deleteDoctor) -> doctorRepository.delete(deleteDoctor));
    }
    
    public DoctorResponse doctorToReponse(Doctor doctor) {
    	return modelMapper.map(doctor, DoctorResponse.class);
    }
   
    
}


