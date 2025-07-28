package com.example.etudiant_crud.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Semestre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomSemestre;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;


      public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNomSemestre() {
        return nomSemestre;
    }

    public void setNomSemestre(String nomSemestre) {
        this.nomSemestre = nomSemestre;
    }

      public LocalDate getDateDebut(){
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
    
   
    public void setDateEmbauche(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

     public LocalDate getDateFin(){
        return dateFin;
    }
   
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    
}
