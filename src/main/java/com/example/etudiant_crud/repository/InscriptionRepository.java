package com.example.etudiant_crud.repository;

import com.example.etudiant_crud.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
        boolean existsByEtudiantIdAndSemestreId(Long etudiantId, Long semestreId);
}
