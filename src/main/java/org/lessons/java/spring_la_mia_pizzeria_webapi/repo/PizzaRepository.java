package org.lessons.java.spring_la_mia_pizzeria_webapi.repo;

import org.lessons.java.spring_la_mia_pizzeria_webapi.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
