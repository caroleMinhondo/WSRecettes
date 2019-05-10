package fr.afcepf.al33.dto;

import java.io.Serializable;

public class EtapeRecetteSelectionnee implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String etape;
	
	public EtapeRecetteSelectionnee() {
		super();
	}

	public EtapeRecetteSelectionnee(Integer id, String etape) {
		super();
		this.id = id;
		this.etape = etape;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEtape() {
		return etape;
	}

	public void setEtape(String etape) {
		this.etape = etape;
	}

	@Override
	public String toString() {
		return "EtapeRecetteSelectionnee [id=" + id + ", etape=" + etape + "]";
	}


	
}
