package br.com.aijalon.medical.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SpecialtyRequest {
	private Long id;
	
	private String name;

    private String description;

    private Boolean active;

}
