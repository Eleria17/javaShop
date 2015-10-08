package org.eleria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="boutiques")
public class boutiques {
	  	@Id
	    @Column(name="ID")
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	     private int zone;
	     
	    public int getZone() {
			return zone;
		}

		public void setZone(int zone) {
			this.zone = zone;
		}

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

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		public float getLocalisation_x() {
			return localisation_x;
		}

		public void setLocalisation_x(float localisation_x) {
			this.localisation_x = localisation_x;
		}

		public float getLocalisation_y() {
			return localisation_y;
		}

		public void setLocalisation_y(float localisation_y) {
			this.localisation_y = localisation_y;
		}

		public int getCategorie() {
			return categorie;
		}

		public void setCategorie(int categorie) {
			this.categorie = categorie;
		}

		private String nom;
	     
	    private String texte;
	    private String textecourt;
	    private String nom_en;
	     
	    private String texte_en;
	    private String textecourt_en;
	    public String getNom_en() {
			return nom_en;
		}

		public void setNom_en(String nom_en) {
			this.nom_en = nom_en;
		}

		public String getTexte_en() {
			return texte_en;
		}

		public void setTexte_en(String texte_en) {
			this.texte_en = texte_en;
		}

		public String getTextecourt_en() {
			return textecourt_en;
		}

		public void setTextecourt_en(String textecourt_en) {
			this.textecourt_en = textecourt_en;
		}

		public String getTextecourt() {
			return textecourt;
		}

		public void setTextecourt(String texte_court) {
			this.textecourt = texte_court;
		}

		private String adresse;
	    
	    private float localisation_x;
	    
	    private float localisation_y;	    
	
		private int categorie;
				
}
