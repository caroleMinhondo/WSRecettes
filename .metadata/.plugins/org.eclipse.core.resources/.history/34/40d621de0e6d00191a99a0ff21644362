package fr.afcepf.al33.client.delegate;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;

import fr.afcepf.al33.dto.Devise;

public class DeviseDelegateRest implements DeviseDelegate {

	private Client jaxrs2client;
	private String debutUrlDevise="http://localhost:8080/springBootWebService/rest/devises";
	private String debutUrlDevisePublic="http://localhost:8080/springBootWebService/rest/public/devises";
	public DeviseDelegateRest() {
		this.jaxrs2client = ClientBuilder.newClient()
				            .register(JacksonFeature.class);
	}
	

	@Override
	public Devise sauvegarderDevise(Devise d) {
		WebTarget devisesTarget = jaxrs2client.target(debutUrlDevise);
		Response responseSavedDevise =  
				devisesTarget.request(MediaType.APPLICATION_JSON_TYPE)
		            .post(Entity.entity(d, MediaType.APPLICATION_JSON_TYPE) );
		if(responseSavedDevise.getStatus()==200 /*OK*/) {
			return responseSavedDevise.readEntity(Devise.class);
		}
		else 
			return null;
	}

	@Override
	public List<Devise> toutesDevises() {
		WebTarget devisesTarget = jaxrs2client.target(debutUrlDevisePublic);
		Devise[] tabDevises= 
				devisesTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .get().readEntity(Devise[].class);
		return Arrays.asList(tabDevises);
	}

}
