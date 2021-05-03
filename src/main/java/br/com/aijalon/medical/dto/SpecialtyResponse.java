package br.com.aijalon.medical.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SpecialtyResponse {
	
		private Long specialtyId;
	
		private String name;

	    private String description;

	    private Boolean active;

	}

