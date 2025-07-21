package com.example.etudiant_crud.controller;

import com.example.etudiant_crud.model.Etudiant;
import com.example.etudiant_crud.repository.EtudiantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/etudiants")
public class EtudiantController {

    private final EtudiantRepository etudiantRepository;

    public EtudiantController(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @GetMapping
    public String listeEtudiants(Model model) {
        model.addAttribute("etudiants", etudiantRepository.findAll());
        return "liste";
    }

    @GetMapping("/ajouter")
    public String formulaireAjout(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "formulaire";
    }

    @PostMapping("/ajouter")
    public String ajouterEtudiant(@ModelAttribute Etudiant etudiant) {
        etudiantRepository.save(etudiant);
        return "redirect:/etudiants";
    }

    @GetMapping("/modifier/{id}")
    public String formulaireModif(@PathVariable Long id, Model model) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Étudiant introuvable : " + id));
        model.addAttribute("etudiant", etudiant);
        return "formulaire";
    }

    @PostMapping("/modifier")
    public String modifierEtudiant(@ModelAttribute Etudiant etudiant) {
        etudiantRepository.save(etudiant);
        return "redirect:/etudiants";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimerEtudiant(@PathVariable Long id) {
        etudiantRepository.deleteById(id);
        return "redirect:/etudiants";
    }
}
