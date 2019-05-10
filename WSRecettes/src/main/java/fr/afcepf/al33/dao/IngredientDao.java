package fr.afcepf.al33.dao;

import org.springframework.data.repository.CrudRepository;

import fr.afcepf.al33.entity.Ingredient;

public interface IngredientDao extends CrudRepository<Ingredient,String>{
	/*
	//méthodes héritées de CrudRepository:
	*/
	
}
