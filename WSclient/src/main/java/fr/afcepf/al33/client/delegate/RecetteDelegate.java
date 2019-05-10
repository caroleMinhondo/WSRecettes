package fr.afcepf.al33.client.delegate;

import java.util.List;

import fr.afcepf.al33.dto.RecetteSelectionnee;

public interface RecetteDelegate {
	
	List<RecetteSelectionnee> recettesSelectionnees(List<String> listeArticle);
	
}
