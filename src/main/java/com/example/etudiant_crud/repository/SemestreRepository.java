package com.example.etudiant_crud.repository;


import com.example.etudiant_crud.model.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestreRepository extends JpaRepository<Semestre, Long> {
}
