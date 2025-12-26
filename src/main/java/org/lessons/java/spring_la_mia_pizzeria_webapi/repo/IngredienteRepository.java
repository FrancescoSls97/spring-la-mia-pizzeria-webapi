package org.lessons.java.spring_la_mia_pizzeria_webapi.repo;

import org.lessons.java.spring_la_mia_pizzeria_webapi.models.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {

}
