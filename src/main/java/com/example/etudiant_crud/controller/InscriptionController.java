package com.example.etudiant_crud.controller;

import com.example.etudiant_crud.model.Inscription;
import com.example.etudiant_crud.model.Etudiant;
import com.example.etudiant_crud.model.Semestre;
import com.example.etudiant_crud.repository.InscriptionRepository;
import com.example.etudiant_crud.repository.EtudiantRepository;
import com.example.etudiant_crud.repository.SemestreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private SemestreRepository semestreRepository;

    @GetMapping("/ajouter")
    public String showForm(Model model) {
        model.addAttribute("inscription", new Inscription());
        model.addAttribute("etudiants", etudiantRepository.findAll());
        model.addAttribute("semestres", semestreRepository.findAll());

        return "ajouterInscription";
    }

    @PostMapping("/ajouter")
    public String ajouterInscription(@ModelAttribute Inscription inscription) {
        inscription.setDateCreation(LocalDate.now());
        inscriptionRepository.save(inscription);
        return "redirect:/inscriptions";
    }

    @GetMapping
    public String listeInscriptions(Model model) {
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        model.addAttribute("inscriptions", inscriptions);
        return "listeInscription";
    }
}
