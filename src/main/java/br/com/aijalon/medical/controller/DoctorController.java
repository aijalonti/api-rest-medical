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
import br.com.aijalon.medical.dto.DoctorUpdateRequest;
import br.com.aijalon.medical.model.Doctor;
import br.com.aijalon.medical.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "doctor")
@Api(value = "Médicos")
public class DoctorController {
	
	@Autowired
    private DoctorService doctorService;
	
	@ApiOperation(value = "Cria Médico")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna sucesso ao criar médico"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Recurso não econtrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody DoctorRequest doctorRequest){
        doctorService.createDoctor(doctorRequest);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }
    
	@ApiOperation(value = "Lista todos os médicos")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de médicos"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Recurso não econtrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
    @GetMapping()
    public List<DoctorResponse> getAll(){
    	return doctorService.getAll();
    }
	
	@ApiOperation(value = "Lista médicos por Id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de médicos por Id"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Recurso não econtrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
    @GetMapping("{id}")
    public ResponseEntity<DoctorResponse> findById(@PathVariable Long id) {
    	DoctorResponse doctor = doctorService.getDoctorById(id);
    	return ResponseEntity.ok().body(doctor);
    }
    
	@ApiOperation(value = "Altera os dados de médico")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Altera os dados de médico"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Recurso não econtrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
    @PatchMapping("{id}")
    public ResponseEntity<?> updateDoctor(@Valid @PathVariable Long id, @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
    	doctorService.updateDoctor(id, doctorUpdateRequest);
    	return ResponseEntity.noContent().build();
    }
    
	@ApiOperation(value = "Deleta médico por Id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Deleta médico por Id"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Recurso não econtrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
    	doctorService.deleteDoctor(id);
    	return ResponseEntity.noContent().build();

    }


}
