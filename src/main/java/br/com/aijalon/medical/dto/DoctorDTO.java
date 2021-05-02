package br.com.aijalon.medical.dto;

import br.com.aijalon.medical.model.Doctor;
import br.com.aijalon.medical.model.Specialty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDTO{

    private Long id;

    private String name;

    private String birthDate;

    private Boolean active;
    
    private Specialty specialty;

    public Doctor toEntity(){

        Doctor doctor = new Doctor();
        
        if(this.getId() != null) {
            doctor.setId(this.getId());
        }
        doctor.setName(this.getName());
        doctor.setBirthDate(this.getBirthDate());
        doctor.setActive(true);
        doctor.setSpecialty(this.specialty);

        return doctor;

    }
}