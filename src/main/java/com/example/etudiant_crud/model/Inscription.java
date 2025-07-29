package com.example.etudiant_crud.model;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Inscription {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateCreation;


    @ManyToOne
    @JoinColumn(name = "etudiant_id") 
    private Etudiant etudiant;


    @ManyToOne
    @JoinColumn(name = "semestre_id") 
    private Semestre semestre;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCreation(){
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation){
        this.dateCreation= dateCreation;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    
    

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }
    
}
