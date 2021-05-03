package br.com.aijalon.medical.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "specialty")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "specialtyId")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialtyId;

    @Column
    @NotEmpty()
    @Size(min = 4, max = 20)
    private String name;

    @Column
    private String description;

    @Column
    private Boolean active;

    @ManyToMany(mappedBy = "specialties")
    private List<Doctor> doctor = new ArrayList<>();
}