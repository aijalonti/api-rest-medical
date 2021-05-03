package br.com.aijalon.medical.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aijalon.medical.dto.DoctorRequest;
import br.com.aijalon.medical.dto.DoctorResponse;
import br.com.aijalon.medical.model.Doctor;
import br.com.aijalon.medical.service.DoctorService;

@RestController
@RequestMapping(value = "doctors")
public class DoctorController {
	
	@Autowired
    private DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody DoctorRequest doctorRequest){
        doctorService.createDoctor(doctorRequest);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }
    
    @GetMapping()
    public List<DoctorResponse> listAll(){
    	return doctorService.findAll();
    }
    
    @GetMapping("{id}")
    public ResponseEntity<DoctorResponse> findById(@PathVariable Long id) {
    	DoctorResponse doctor = doctorService.findDoctorById(id);
    	return ResponseEntity.ok().body(doctor);
    }
    
    @PatchMapping("{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody DoctorRequest doctorRequest) {
    	doctorService.updateDoctor(id, doctorRequest);
    	return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
    	doctorService.deleteDoctor(id);
    	return ResponseEntity.noContent().build();

    }


}
