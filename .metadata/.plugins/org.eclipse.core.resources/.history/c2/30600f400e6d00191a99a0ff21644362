package fr.afcepf.al33.client;

import fr.afcepf.al33.client.delegate.ConvertisseurDelegate;
import fr.afcepf.al33.client.delegate.ConvertisseurDelegateSoap;
import fr.afcepf.al33.client.delegate.DeviseDelegate;
import fr.afcepf.al33.client.delegate.DeviseDelegateRest;
import fr.afcepf.al33.dto.Devise;

public class TestAppelWsSoapEtRestApp {

	
    //à lancer via Run as / java application
	public static void main(String[] args) {
		
		DeviseDelegate deviseDelegate = new DeviseDelegateRest();
		Devise savedDevise = deviseDelegate.sauvegarderDevise(
				new Devise("codeQuiVaBien","monnaieSinge",0.00001));
		System.out.println("savedDevise="+savedDevise);
		System.out.println("toutesDevises="+deviseDelegate.toutesDevises());
		
		ConvertisseurDelegate convertisseurDelegate 
		//= new ConvertisseurDelegateRest();
		= new ConvertisseurDelegateSoap();
		double montantConverti = 
				convertisseurDelegate.convertir(50, "EUR", "USD");
		System.out.println("montantConverti="+montantConverti);
		
	
		System.out.println("15 euros=" + 
					convertisseurDelegate.euroToFranc(15.0));
		System.out.println("auteur=" + convertisseurDelegate.getAuteur());
		
	}

}
