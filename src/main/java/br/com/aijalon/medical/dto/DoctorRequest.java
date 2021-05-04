package br.com.aijalon.medical.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorRequest{
	
	@NotBlank(message = "{name.not.blank}")
	@Size(min = 5, max = 60, message = "")
	private String name;
	
	@NotBlank(message = "{date.birth.not.blank}")
    private String birthDate;
    
    private List<Long> specialties;

}