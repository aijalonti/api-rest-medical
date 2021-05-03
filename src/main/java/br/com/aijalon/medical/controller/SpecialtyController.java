package br.com.aijalon.medical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aijalon.medical.dto.SpecialtyDoctorResponse;
import br.com.aijalon.medical.dto.SpecialtyRequest;
import br.com.aijalon.medical.dto.SpecialtyResponse;
import br.com.aijalon.medical.model.Specialty;
import br.com.aijalon.medical.service.SpecialtyService;

@RestController
@RequestMapping(value = "specialty")
public class SpecialtyController {
	
	@Autowired
	private SpecialtyService specialtyService;
	
	@PostMapping()
	public ResponseEntity<String> createSpecialty(@RequestBody SpecialtyRequest specialtyRequest){
		specialtyService.createSpecialty(specialtyRequest);
        return new ResponseEntity<>("Especialidade criada com sucesso", HttpStatus.CREATED);
    }
	
	@GetMapping()
	public List<SpecialtyResponse>findAll() {
		return specialtyService.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<SpecialtyResponse> findById(@PathVariable Long id) {
		SpecialtyResponse specialty = specialtyService.findSpecialtyById(id);
		return ResponseEntity.ok().body(specialty);
	}
	
	@GetMapping("listSpecialtiesDoctors/{id}")
	public ResponseEntity<SpecialtyDoctorResponse>findDoctorSpecialtyById(@PathVariable Long id) {
		SpecialtyDoctorResponse specialtyDoctor = specialtyService.findDoctorSpecialtyById(id);
		return ResponseEntity.ok().body(specialtyDoctor);
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<?> updateSpecialty(@PathVariable Long id, SpecialtyRequest specialtyRequest){
		specialtyService.updateSpecialty(id, specialtyRequest);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteSpecialty(@PathVariable Long id){
		specialtyService.deleteSpecialty(id);
		return ResponseEntity.accepted().build();
	}
}	
