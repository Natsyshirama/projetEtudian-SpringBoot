package com.example.etudiant_crud.repository;

import com.example.etudiant_crud.model.Prof;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfRepository extends JpaRepository<Prof, Long> {
}
