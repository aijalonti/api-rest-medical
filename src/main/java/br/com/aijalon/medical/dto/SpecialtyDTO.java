package br.com.aijalon.medical.dto;

import br.com.aijalon.medical.model.Doctor;
import br.com.aijalon.medical.model.Specialty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SpecialtyDTO {

    private Long id;

    private String name;

    private String description;

    private Boolean active;

    private Doctor doctor;

    public Specialty toEntity(){
        Specialty specialty = new Specialty();

        if(this.getId() != null) {
            specialty.setId(this.getId());
        }
        specialty.setName(this.getName());
        specialty.setDescription(this.description);
        specialty.setActive(true);
        specialty.setDoctor(this.doctor);

        return specialty;
    }
    
}
