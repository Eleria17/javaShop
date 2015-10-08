package org.eleria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="article")
public class article {
	
	  @Id
	    @Column(name="ID")
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	  
	    private String nom;
	     
	    private String texte;
	    private float poids;
	    private int quantiteDispo;
	    
	    public int getQuantiteDispo() {
			return quantiteDispo;
		}

		public void setQuantiteDispo(int quantiteDispo) {
			this.quantiteDispo = quantiteDispo;
		}

		public float getPoids() {
			return poids;
		}

		public void setPoids(float poids) {
			this.poids = poids;
		}

		@Column(name="BOUTIQUE")
	    private int boutique;
	    private float prix;
	    
	    private float tva;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getTexte() {
			return texte;
		}

		public void setTexte(String texte) {
			this.texte = texte;
		}

		public int getBoutique() {
			return boutique;
		}

		public void setBoutique(int boutique) {
			this.boutique = boutique;
		}

		public float getPrix() {
			return prix;
		}

		public void setPrix(float prix) {
			this.prix = prix;
		}

		public float getTva() {
			return tva;
		}

		public void setTva(float tva) {
			this.tva = tva;
		}

}
