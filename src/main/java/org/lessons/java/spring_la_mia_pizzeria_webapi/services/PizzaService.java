package org.lessons.java.spring_la_mia_pizzeria_webapi.services;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_webapi.models.Offerta;
import org.lessons.java.spring_la_mia_pizzeria_webapi.models.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repo.OffertaRepository;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OffertaRepository offerrtaRepository;

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public Pizza getById(Integer id) {
        return pizzaRepository.findById(id).get();
    }

    public Pizza create(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delete(Pizza pizza) {
        for (Offerta offerta : pizza.getOfferta()) {
            offerrtaRepository.delete(offerta);
        }

        pizzaRepository.delete(pizza);
    }
}
