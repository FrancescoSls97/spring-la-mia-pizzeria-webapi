package org.lessons.java.spring_la_mia_pizzeria_webapi.controllers;

import org.lessons.java.spring_la_mia_pizzeria_webapi.models.Offerta;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repo.OffertaRepository;
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
@RequestMapping("/offerta")
public class OffertaController {

    @Autowired
    private OffertaRepository offertaRepository;

    // create
    @PostMapping("/create")
    private String store(@Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "offerta/create-edit";
        }
        // salvataggio dati
        offertaRepository.save(formOfferta);
        return "redirect:/pizza/" + formOfferta.getPizza().getId();
    }

    // edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("offerta", offertaRepository.findById(id).get());
        model.addAttribute("edit", true);
        return "offerta/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("offerta") Offerta formOfferta,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "offerta/create-edit";
        }

        offertaRepository.save(formOfferta);
        return "redirect:/pizza/" + formOfferta.getPizza().getId();
    }
}
