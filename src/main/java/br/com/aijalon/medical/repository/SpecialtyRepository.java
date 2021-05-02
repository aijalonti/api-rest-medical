package br.com.aijalon.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aijalon.medical.model.Specialty;



@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    
}
