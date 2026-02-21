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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/inscriptions")
public class InscriptionController {

    private final InscriptionRepository inscriptionRepository;
    private final EtudiantRepository etudiantRepository;
    private final SemestreRepository semestreRepository;

    public InscriptionController(InscriptionRepository inscriptionRepository, 
                                 EtudiantRepository etudiantRepository, 
                                 SemestreRepository semestreRepository) {
        this.inscriptionRepository = inscriptionRepository;
        this.etudiantRepository = etudiantRepository;
        this.semestreRepository = semestreRepository;
    }

    // Formulaire
    @GetMapping("/ajouter")
    public String afficherFormulaireInscription(Model model) {
        model.addAttribute("semestres", semestreRepository.findAll());
        return "ajouterInscription";
    }

    // Vérification
    @PostMapping("/verifier")
    public String verifierInscription(@RequestParam Long etudiantId,
                                      @RequestParam Long semestreId,
                                      Model model) {
        boolean existe = inscriptionRepository.existsByEtudiantIdAndSemestreId(etudiantId, semestreId);
        model.addAttribute("etudiantId", etudiantId);
        model.addAttribute("semestreId", semestreId);
        model.addAttribute("semestres", semestreRepository.findAll());

        if (existe) {
            model.addAttribute("message", "L'étudiant avec ID = " + etudiantId + " est déjà inscrit dans le semestre ID = " + semestreId);
            return "ajouterInscription";
        }

        model.addAttribute("confirmation", true);
        return "ajouterInscription";
    }

    // Enregistrement
    @PostMapping("/confirmer")
    public String confirmerInscription(@RequestParam Long etudiantId,
                                       @RequestParam Long semestreId,
                                       RedirectAttributes redirectAttributes) {
        Optional<Etudiant> etuOpt = etudiantRepository.findById(etudiantId);
        Optional<Semestre> semOpt = semestreRepository.findById(semestreId);

        if (etuOpt.isEmpty() || semOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Étudiant ou semestre invalide.");
            return "redirect:/inscriptions/ajouter";
        }

        Inscription inscription = new Inscription();
        inscription.setEtudiant(etuOpt.get());
        inscription.setSemestre(semOpt.get());
        inscription.setDateCreation(LocalDate.now());

        inscriptionRepository.save(inscription);

        redirectAttributes.addFlashAttribute("success", "Inscription enregistrée avec succès !");
        return "redirect:/inscriptions/ajouter";
    }

    @GetMapping
    public String listeInscriptions(Model model) {
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        model.addAttribute("inscriptions", inscriptions);
        return "listeInscription";
    }
}

