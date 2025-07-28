package com.example.etudiant_crud.controller;

import com.example.etudiant_crud.model.Semestre;
import com.example.etudiant_crud.repository.SemestreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/semestre")
public class SemestreController {
    private final SemestreRepository semestreRepository;

    public SemestreController(SemestreRepository semestreRepository){
        this.semestreRepository = semestreRepository;
    }

    @GetMapping
    public String listeSemestre(Model model){
        model.addAttribute("semestres", semestreRepository.findAll());
        return "listeSemestre";
    }

    @GetMapping("/ajouterSemestre")
    public String formulaire(Model model ) {
        model.addAttribute("semestre", new Semestre());
        return ("formulaireSemestre");
    }

    @PostMapping("/ajouterSemestre")
    public String ajouteSemestre(@ModelAttribute Semestre semestre){
        semestreRepository.save(semestre);
        return "redirect:/semestre";
    }
    
}
