package br.com.aijalon.medical.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SpecialtyDoctorResponse {
	
	private Long specialtyId;
	
	private String name;

    private String description;
    
    private  List<DoctorNameResponse> doctor;
}
