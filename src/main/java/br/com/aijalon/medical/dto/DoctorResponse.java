package br.com.aijalon.medical.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorResponse {
	
	private Long doctorId;
	
	private String name;

    private String birthDate;

    private Boolean active;
}
