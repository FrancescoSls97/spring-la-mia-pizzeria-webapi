package org.lessons.java.spring_la_mia_pizzeria_webapi.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_webapi.models.Offerta;
import org.lessons.java.spring_la_mia_pizzeria_webapi.models.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repo.PizzaRepository;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repo.IngredienteRepository;
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
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OffertaRepository offertaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    // index
    @GetMapping
    public String index(Model model) {
        List<Pizza> pizza = pizzaRepository.findAll();
        model.addAttribute("pizza", pizza);
        return "pizza/index";
    }

    // show
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = pizzaRepository.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizza/show";
    }

    // create view
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredienti", ingredienteRepository.findAll());

        return "pizza/create";
    }

    // create
    @PostMapping("create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredienti", ingredienteRepository.findAll());

            return "pizza/create";
        }
        // salvataggio dati
        pizzaRepository.save(formPizza);
        return "redirect:/pizza";
    }

    // edit view
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        model.addAttribute("ingredienti", ingredienteRepository.findAll());
        return "pizza/edit";
    }

    // update
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredienti", ingredienteRepository.findAll());
            return "pizza/edit";
        }
        // salvataggio dati
        pizzaRepository.save(formPizza);
        return "redirect:/pizza";
    }

    // delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Pizza pizza = pizzaRepository.findById(id).get();
        for (Offerta offerta : pizza.getOfferta()) {
            offertaRepository.delete(offerta);
        }
        pizzaRepository.deleteById(id);
        return "redirect:/pizza";
    }

    @GetMapping("/{id}/offerta")
    public String offerta(@PathVariable Integer id, Model model) {
        Offerta offerta = new Offerta();
        offerta.setPizza(pizzaRepository.findById(id).get());
        model.addAttribute("offerta", offerta);
        return "offerta/create-edit";
    }

}
