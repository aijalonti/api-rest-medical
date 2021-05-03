package br.com.aijalon.medical.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "doctor")
//Não duplica a consulta por causa do manyTomany
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "doctorId")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @Column
    @NotEmpty()
    @Size(min = 4, max = 20)
    private String name;
    
    @NotNull()
    @Column(name = "birth_date")
    private String birthDate;

    @Column
    private Boolean active;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "doctor_specialty",
    joinColumns = @JoinColumn(name = "doctor_id"),
    inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private List<Specialty> specialties = new ArrayList<>();
}