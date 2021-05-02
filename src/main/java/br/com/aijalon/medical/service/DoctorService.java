package br.com.aijalon.medical.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aijalon.medical.dto.DoctorDTO;
import br.com.aijalon.medical.exception.ResourceNotFoundException;
import br.com.aijalon.medical.model.Doctor;
import br.com.aijalon.medical.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorDTO.toEntity();

        return doctorRepository.save(doctor);
    }

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }

    public Doctor findById(Long id){
        return doctorRepository.findById(id)
                               .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
    }

    public void updateDoctor(DoctorDTO doctorDTO) {
       Doctor doctor = doctorDTO.toEntity();
       Optional<Doctor> optionalDoctor = Optional.of(this.findById(doctor.getId()));
       optionalDoctor.ifPresentOrElse((updateDoctor) -> doctorRepository.save(updateDoctor), 
                                     () -> new ResourceNotFoundException("Médico não encontrado"));
    }

    public void deleteDoctor(Long id){
        Optional<Doctor> optionalDoctor = Optional.of(this.findById(id));
        optionalDoctor.ifPresentOrElse((deleteDoctor) -> doctorRepository.delete(deleteDoctor),
                                      () -> new ResourceNotFoundException("Médico não encontrado"));
    }
    
}


