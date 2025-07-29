package com.example.etudiant_crud.controller;

import com.example.etudiant_crud.model.Prof;
import com.example.etudiant_crud.model.Semestre;
import com.example.etudiant_crud.model.Matiere;
import com.example.etudiant_crud.repository.MatiereRepository;
import com.example.etudiant_crud.repository.ProfRepository;
import com.example.etudiant_crud.repository.SemestreRepository;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/matieres")
public class MatiereController {
    
    private final MatiereRepository matiereRepository;
    private final SemestreRepository semestreRepository;
    private final ProfRepository profRepository;

    public MatiereController(MatiereRepository matiereRepository,
                            SemestreRepository semestreRepository,
                            ProfRepository profRepository){
        this.matiereRepository = matiereRepository;
        this.semestreRepository = semestreRepository;
        this.profRepository = profRepository;
    }

      @GetMapping
    public String listeMatiere(Model model) {
        
        model.addAttribute("matieres", matiereRepository.findAll());
        return "listeMatiere";
    }

     @GetMapping("/ajouterMatiere")
    public String formulairMatiere(Model model) {
        model.addAttribute("matiere", new Matiere());
        model.addAttribute("profs", profRepository.findAll());
        model.addAttribute("semestres", semestreRepository.findAll());
        return "formulaireMatiere";
    }

    @PostMapping("/ajouterMatiere")
    public String ajouterMatiere(@ModelAttribute Matiere matiere) {
        matiereRepository.save(matiere);
        return "redirect:/matieres";
    }

    @GetMapping("/modifierMatiere/{id}")
    public String formulaireModif(@PathVariable Long id, Model model) {
        Matiere matiere = matiereRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matiere introuvable : " + id));
        model.addAttribute("matiere", matiere);
        model.addAttribute("profs", profRepository.findAll());
        model.addAttribute("semestres", semestreRepository.findAll());
        return "formulaireMatiere";
    }


    @PostMapping("/modifierMatiere")
    public String modifierMatiere(@ModelAttribute Matiere matiere) {
        matiereRepository.save(matiere);
        return "redirect:/matieres";
    }

    
}
