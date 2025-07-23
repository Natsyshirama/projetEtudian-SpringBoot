package com.example.etudiant_crud.controller;

import com.example.etudiant_crud.model.Prof;
import com.example.etudiant_crud.repository.ProfRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profs")
public class ProfController {
    
    private final ProfRepository profRepository;

    public ProfController(ProfRepository profRepository){
        this.profRepository = profRepository;
    }

    @GetMapping
    public String listeProf(Model model){
        model.addAttribute("prof", profRepository.findAll());
        return "listeProf";
    }

     @GetMapping("/ajouterProf")
    public String formulaireAjout(Model model) {
        model.addAttribute("prof", new Prof());
        return "formulairProf";
    }

    @PostMapping("/ajouterProf")
    public String ajouterProf(@ModelAttribute Prof prof){
        profRepository.save(prof);
        return "redirect:/profs";
    }

    @GetMapping("/modifierProf/{id}")
    public String fomulaireModifProf(@PathVariable Long id, Model model){
        Prof prof = profRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Prof Introuvable" + id));

        model.addAttribute("prof", prof);
        return "formulairProf";
    }

    @PostMapping("/modif")
    public String modifierProf(@ModelAttribute Prof prof){
        profRepository.save(prof);
        return "redirect:/profs";
    }
    
}
