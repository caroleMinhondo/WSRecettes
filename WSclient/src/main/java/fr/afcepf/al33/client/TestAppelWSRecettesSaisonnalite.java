package fr.afcepf.al33.client;

import java.util.ArrayList;
import java.util.List;

import fr.afcepf.al33.client.delegate.RecetteDelegate;
import fr.afcepf.al33.client.delegate.RecetteDelegateRest;
import fr.afcepf.al33.dto.RecetteSelectionnee;

public class TestAppelWSRecettesSaisonnalite {

	
    //à lancer via Run as / java application
	public static void main(String[] args) {
		
		RecetteDelegate recetteDelegate = new RecetteDelegateRest();
		
		List<String> listeArticle = new ArrayList<String>();
		listeArticle.add("Panais");
		listeArticle.add("Pomme de terre rouge");
		listeArticle.add("Ail");
		System.out.println("coucou 1");	
		
		List<RecetteSelectionnee> recetteSelectionnees = new ArrayList<RecetteSelectionnee>();
		recetteSelectionnees = recetteDelegate.recettesSelectionnees(listeArticle);
		for (RecetteSelectionnee recetteSelectionnee : recetteSelectionnees) 
		{
			System.out.println("recetteSelectionnee = "+recetteSelectionnee.toString());
		}
		
		System.out.println("coucou 2");
		
		
	}

}
