package com.example.etudiant_crud.repository;

import com.example.etudiant_crud.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
