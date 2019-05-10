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
@Table(name="ingredient")
public class Ingredient implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Integer id;
	
	@Column(name="nom")
	private String nom;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="ingredient", cascade=CascadeType.ALL)
	private List<QuantiteIngredient> quantiteIngredients;

	public Ingredient() {
		super();
	}

	public Ingredient(Integer id, String nom, List<QuantiteIngredient> quantiteIngredients) {
		super();
		this.id = id;
		this.nom = nom;
		this.quantiteIngredients = quantiteIngredients;
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

	public List<QuantiteIngredient> getQuantiteIngredients() {
		return quantiteIngredients;
	}

	public void setQuantiteIngredients(List<QuantiteIngredient> quantiteIngredients) {
		this.quantiteIngredients = quantiteIngredients;
	}

	
}
