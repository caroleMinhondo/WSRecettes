package fr.afcepf.al33.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="recette")
public class Recette implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Integer id;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="photo")
	private String photo;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="recette", cascade=CascadeType.ALL)
	private List<QuantiteIngredient> quantiteIngredients;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="recette", cascade=CascadeType.ALL)
	private List<EtapeRecette> etapeRecettes;

	public Recette() {
		super();
	}

	public Recette(Integer id, String nom, String photo, List<QuantiteIngredient> quantiteIngredients,
			List<EtapeRecette> etapeRecettes) {
		super();
		this.id = id;
		this.nom = nom;
		this.photo = photo;
		this.quantiteIngredients = quantiteIngredients;
		this.etapeRecettes = etapeRecettes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<QuantiteIngredient> getQuantiteIngredients() {
		return quantiteIngredients;
	}

	public void setQuantiteIngredients(List<QuantiteIngredient> quantiteIngredients) {
		this.quantiteIngredients = quantiteIngredients;
	}

	public List<EtapeRecette> getEtapeRecettes() {
		return etapeRecettes;
	}

	public void setEtapeRecettes(List<EtapeRecette> etapeRecettes) {
		this.etapeRecettes = etapeRecettes;
	}
	

	
}
