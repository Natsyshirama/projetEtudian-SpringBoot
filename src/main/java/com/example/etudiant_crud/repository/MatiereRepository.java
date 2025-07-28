package com.example.etudiant_crud.repository;

import com.example.etudiant_crud.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    List<Matiere> findByProfId(Long profId);

}
