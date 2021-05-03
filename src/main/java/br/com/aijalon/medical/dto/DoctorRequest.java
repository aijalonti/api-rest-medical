package br.com.aijalon.medical.dto;

import java.util.List;
import java.util.Set;

import br.com.aijalon.medical.model.Doctor;
import br.com.aijalon.medical.model.Specialty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorRequest{

	private String name;

    private String birthDate;

    private Boolean active;
    
    private List<Long> specialties;

}