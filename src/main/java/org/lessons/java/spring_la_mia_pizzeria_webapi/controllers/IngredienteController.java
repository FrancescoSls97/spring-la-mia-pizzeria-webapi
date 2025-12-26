package org.lessons.java.spring_la_mia_pizzeria_webapi.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_webapi.models.Ingrediente;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repo.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingrediente")
public class IngredienteController {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    // index
    @GetMapping
    public String index(Model model) {
        List<Ingrediente> ingredienti = ingredienteRepository.findAll();
        model.addAttribute("ingredienti", ingredienti);
        return "ingrediente/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("ingrediente", ingredienteRepository.findById(id).get());
        return "ingrediente/show";
    }

    // create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingrediente", new Ingrediente());
        return "ingrediente/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingrediente") Ingrediente formIngredienti, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "ingrediente/create-edit";
        }
        // salvataggio dati
        ingredienteRepository.save(formIngredienti);
        return "redirect:/ingrediente";
    }

    // edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingrediente", ingredienteRepository.findById(id).get());
        model.addAttribute("edit", true);
        return "ingrediente/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("ingrediente") Ingrediente formIngrediente,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "ingrediente/create-edit";
        }

        ingredienteRepository.save(formIngrediente);
        return "redirect:/ingrediente";
    }
}
