package br.com.aijalon.medical.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SpecialtyRequest {
	
	@NotBlank(message = "{name.not.blank}")
	private String name;

    private String description;

}
