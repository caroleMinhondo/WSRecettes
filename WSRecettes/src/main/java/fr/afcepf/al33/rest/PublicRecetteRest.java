package fr.afcepf.al33.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.al33.dao.EtapeRecetteDao;
import fr.afcepf.al33.dao.IngredientDao;
import fr.afcepf.al33.dao.QuantiteIngredientDao;
import fr.afcepf.al33.dao.RecetteDao;
import fr.afcepf.al33.dto.EtapeRecetteSelectionnee;
import fr.afcepf.al33.dto.IngredientSelectionnee;
import fr.afcepf.al33.dto.RecetteSelectionnee;
import fr.afcepf.al33.entity.EtapeRecette;
import fr.afcepf.al33.entity.Ingredient;
import fr.afcepf.al33.entity.QuantiteIngredient;
import fr.afcepf.al33.entity.Recette;

@CrossOrigin("*")//pour accepter de répondre à des appels ajax 
                 //provenant d'autres domaines/applications/...

@RestController //@Component de type @RestController
@RequestMapping(value="/rest/public/recettes" , headers="Accept=application/json")
public class PublicRecetteRest 

{
	private class RecetteASelectionner  implements Comparable<RecetteASelectionner>
	{
		private int nbreOccurence;
		private Integer idRecette;
		
		private RecetteASelectionner(Integer nbreOccurence, Integer idRecette) 
		{
			super();
			this.nbreOccurence = nbreOccurence;
			this.idRecette = idRecette;
		}
		
		private Integer getNbreOccurence() 
		{
			return nbreOccurence;
		}
		
		private void setNbreOccurence(Integer nbreOccurence) 
		{
			this.nbreOccurence = nbreOccurence;
		}
		
		private Integer getIdRecette() 
		{
			return idRecette;
		}
		
		private void setIdRecette(Integer idRecette) 
		{
			this.idRecette = idRecette;
		}

		@Override
		public int compareTo(RecetteASelectionner o) 
		{
			return (this.nbreOccurence - o.nbreOccurence);
		}
	}
	
	@Autowired //injection le dao (temporairement) 
	private RecetteDao recetteDao;
	
	@Autowired //injection le dao (temporairement) 
	private IngredientDao ingredientDao;

	@Autowired //injection le dao (temporairement) 
	private QuantiteIngredientDao quantiteIngredientDao;

	@Autowired //injection le dao (temporairement) 
	private EtapeRecetteDao etapeRecetteDao;
	
	//URL : ..../rest/public/recettes/selectionner
	//ou 
	//URL : ..../rest/public/recettes/selectionner?listeArticle=Panais&?listeArticle=a2&?listeArticle=a3
	@RequestMapping(value="/selectionner" , method=RequestMethod.GET)
	public List<RecetteSelectionnee> selectionner(@RequestParam(name="listeArticle",required=false)List<String> listeArticle)
				
	{
		for (String art : listeArticle )
		{
			System.out.println("article = "+art);
		}
			
		List<Recette> recettes = new ArrayList<Recette>();
		
		if (listeArticle!=null)
		{
			System.out.println("passage 1");
			recettes = RecetteASelectionnerSurArticles(listeArticle);
		}
		
		else 
		{
			System.out.println("passage 2");
			recettes = (List<Recette>) recetteDao.findAll();
		}
		
		
		
		List<RecetteSelectionnee> recetteSelectionnees = new ArrayList<RecetteSelectionnee>();
		
		for (Recette rec : recettes )
		{
			//mise en forme étape recette
			List<EtapeRecette> etapeRecettes = rec.getEtapeRecettes();
			List<EtapeRecetteSelectionnee> EtapeRecetteSelectionnees = new ArrayList<EtapeRecetteSelectionnee>();
			for (EtapeRecette etapeRecette : etapeRecettes)
			{
				EtapeRecetteSelectionnee etapeRecetteSelectionnee = new EtapeRecetteSelectionnee(etapeRecette.getId(), etapeRecette.getEtape());
				EtapeRecetteSelectionnees.add(etapeRecetteSelectionnee);
			}
			
			//mise en forme ingrédient
			List<QuantiteIngredient> quantiteIngredients = rec.getQuantiteIngredients();
			List<IngredientSelectionnee> IngredientSelectionnees = new ArrayList<IngredientSelectionnee>();
			for (QuantiteIngredient quantiteIngredient : quantiteIngredients)
			{
				IngredientSelectionnee ingredientSelectionnee = new IngredientSelectionnee
						(quantiteIngredient.getId(), quantiteIngredient.getQuantite(), quantiteIngredient.getUnite(), quantiteIngredient.getIngredient().getNom());
				IngredientSelectionnees.add(ingredientSelectionnee);
			}
			
			//création d'un objet RecetteSelectionne
			recetteSelectionnees.add(new RecetteSelectionnee(rec.getId(),rec.getNom(), rec.getPhoto(), IngredientSelectionnees, EtapeRecetteSelectionnees));
			
			
		}
		
		for (RecetteSelectionnee recetteSelectionnee : recetteSelectionnees) 
		{
			System.out.println("recetteSelectionnee = "+recetteSelectionnee.toString());
		}
		
		return recetteSelectionnees;
				
	}
	
	private List<Recette> RecetteASelectionnerSurArticles(List<String> listeArticle)  
	{
		
		List<Ingredient> ingredients = (List<Ingredient>) ingredientDao.findAll();
		
		System.out.println("ingredients = "+ingredients.toString());
		
		List<RecetteASelectionner> recetteASelectionners = new ArrayList<RecetteASelectionner>();
		
		// on parcourt la liste Ingredient de la table
		for (Ingredient ingredient : ingredients)
		{
			System.out.println("ingredient = "+ingredient);
			
			// pour chaque Ingredient, on parcourt la liste des articles sélectionnés dans le panier, en parametre d'entrée du WebService
			for (String art : listeArticle )
			{
				
				System.out.println("article = "+art.toString());
				System.out.println("ingredient.getNom = "+ingredient.getNom());
				
				
				//si article correspond à Ingrédient
				if (art.equals(ingredient.getNom()))
				{
					// on récupère la liste des QuantiteIngredient de Ingredient pour récupérer les id des recettes
					List<QuantiteIngredient> quantiteIngredients = new ArrayList<QuantiteIngredient>(ingredient.getQuantiteIngredients());
					
					System.out.println("quantiteIngredients = "+quantiteIngredients.toString());
					
					// on parcourt la liste des QuantiteIngredient
					for (QuantiteIngredient quantiteIngredient : quantiteIngredients) 
					{
						System.out.println("quantiteIngredient = "+quantiteIngredient);
						// on parcourt la liste RecetteASelectionner pour stocker id recette ou incrémenter le nbrOccurence
						int comptabilise = 0;
						for (RecetteASelectionner recetteASelectionner : recetteASelectionners) 
						{
							if (recetteASelectionner.getIdRecette()==quantiteIngredient.getRecette().getId()) 
							{
								System.out.println("passage 1.1");
								recetteASelectionner.setNbreOccurence(recetteASelectionner.getNbreOccurence()+1);
								comptabilise=1;
							} 
						}
						if (comptabilise==0) 
						{
							System.out.println("passage 1.2");
							RecetteASelectionner recetteASelectionner = new RecetteASelectionner(1,quantiteIngredient.getRecette().getId()); 
							recetteASelectionners.add(recetteASelectionner);
						}
					}
				}
			}
		}
		//tri de la liste des recettees sélectionnées
		Collections.sort(recetteASelectionners);
		System.out.println("recetteASelectionners = "+recetteASelectionners);
		
		//récupération des recettes à partir de la liste triée
		List<Recette> recettes = lectureRecettes(recetteASelectionners);
		System.out.println("recettes = "+recettes);
		
		return recettes;
	} 
	
	private List<Recette> lectureRecettes(List<RecetteASelectionner> recetteASelectionners)  
	{
		List<Recette> recettes = new ArrayList<Recette>();
		for (RecetteASelectionner recetteASelectionner : recetteASelectionners) 
		{
			System.out.println("recetteASelectionner.getIdRecette = "+recetteASelectionner.getIdRecette());
			Recette recette = recetteDao.findById(recetteASelectionner.getIdRecette()).get();
						
			recettes.add(recette);
		}
		return recettes;
	}

}

	
