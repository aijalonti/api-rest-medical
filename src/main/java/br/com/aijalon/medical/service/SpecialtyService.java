package br.com.aijalon.medical.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aijalon.medical.dto.SpecialtyDTO;
import br.com.aijalon.medical.exception.ResourceNotFoundException;
import br.com.aijalon.medical.model.Specialty;
import br.com.aijalon.medical.repository.SpecialtyRepository;

@Service
public class SpecialtyService {
    
    @Autowired
    private SpecialtyRepository specialtyRepository;

    public Specialty createSpecialty(SpecialtyDTO SpecialtyDTO) {
        Specialty specialty = SpecialtyDTO.toEntity();

        return specialtyRepository.save(specialty);
    }

    public List<Specialty> findAll(){
        return specialtyRepository.findAll();
    }

    public Specialty findById(Long id) {
        return specialtyRepository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada!"));
    }

    public void updateSpicialtie(SpecialtyDTO specialtyDTO) {
        Specialty specialty = specialtyDTO.toEntity();
        Optional<Specialty> optionalSpecialty = Optional.of(this.findById(specialty.getId()));
        optionalSpecialty.ifPresentOrElse((specialtyUpdate) -> specialtyRepository.save(specialtyUpdate),
                                          () -> new ResourceNotFoundException("Especialidade não encontrada!"));
    }

    public void deleteSpecialty(Long id) {
        Optional<Specialty> optionalSpecialty = Optional.of(this.findById(id));
        optionalSpecialty.ifPresentOrElse((deleteSpecialty) -> specialtyRepository.delete(deleteSpecialty),
                                          () -> new ResourceNotFoundException("Especialidade não encontrada!"));
    }
}
