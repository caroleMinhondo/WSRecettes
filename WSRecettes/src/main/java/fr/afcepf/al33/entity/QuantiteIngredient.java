package fr.afcepf.al33.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="quantite_ingredient")
public class QuantiteIngredient implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Integer id;
	
	@Column(name="quantite")
	private double quantite;
	
	@Column(name="unite")
	private String unite;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id")
	private Recette recette;

	@ManyToOne
	@JoinColumn(referencedColumnName="id")
	private Ingredient ingredient;

	public QuantiteIngredient() {
		super();
	}

	public QuantiteIngredient(Integer id, double quantite, String unite, Recette recette, Ingredient ingredient) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.unite = unite;
		this.recette = recette;
		this.ingredient = ingredient;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

		
}
