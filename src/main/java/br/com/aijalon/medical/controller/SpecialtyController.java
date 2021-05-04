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
import br.com.aijalon.medical.service.SpecialtyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value = "specialty")
@Api(value = "Especialidades")
public class SpecialtyController {
	
	@Autowired
	private SpecialtyService specialtyService;
	
	@ApiOperation(value = "Cria especialidades")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a sucesso ao criar especialidade"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Recurso não econtrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@PostMapping()
	public ResponseEntity<String> createSpecialty(@RequestBody SpecialtyRequest specialtyRequest){
		specialtyService.createSpecialty(specialtyRequest);
        return new ResponseEntity<>("Especialidade criada com sucesso", HttpStatus.CREATED);
    }
	
	@ApiOperation(value = "Lista todas as especialidades")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de especialidades"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Recurso não econtrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@GetMapping()
	public List<SpecialtyResponse>getAll() {
		return specialtyService.getAll();
	}
	
	@ApiOperation(value = "Lista especialidade por Id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de especialidade por Id"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Recurso não econtrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@GetMapping("{id}")
	public ResponseEntity<SpecialtyResponse> findById(@PathVariable Long id) {
		SpecialtyResponse specialty = specialtyService.getSpecialtyById(id);
		return ResponseEntity.ok().body(specialty);
	}
	
	@ApiOperation(value = "Lista os médicos de uma determinada especialidade")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de médicos de uma determinada especialidade"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Recurso não econtrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@GetMapping("{id}/listSpecialtiesDoctors")
	public ResponseEntity<SpecialtyDoctorResponse>getDoctorSpecialtyById(@PathVariable Long id) {
		SpecialtyDoctorResponse specialtyDoctor = specialtyService.getDoctorSpecialtyById(id);
		return ResponseEntity.ok().body(specialtyDoctor);
	}
	
	@ApiOperation(value = "Altera os dados de especialidade")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Altera os dados de especialidade"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Recurso não econtrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@PatchMapping("{id}")
	public ResponseEntity<?> updateSpecialty(@PathVariable Long id, @RequestBody SpecialtyRequest specialtyRequest){
		specialtyService.updateSpecialty(id, specialtyRequest);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Deleta especialidade por Id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Deleta especialidade por Id"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Recurso não econtrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteSpecialty(@PathVariable Long id){
		specialtyService.deleteSpecialty(id);
		return ResponseEntity.noContent().build();
	}
}	
