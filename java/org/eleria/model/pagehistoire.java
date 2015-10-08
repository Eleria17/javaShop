package org.eleria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pagehistoire")
public class pagehistoire {
	  @Id
	    @Column(name="ID")
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	     private int zone;
	     private Integer position;
	    public Integer getPosition() {
			return position;
		}

		public void setPosition(Integer position) {
			this.position = position;
		}

		public int getZone() {
			return zone;
		}

		public void setZone(int zone) {
			this.zone = zone;
		}

		private String nom;
		private String nom_en; 
	    public String getNom_en() {
			return nom_en;
		}

		public void setNom_en(String nom_en) {
			this.nom_en = nom_en;
		}

		private String texte;
	    
	    private String mots_cles;
	    
	    private String textecourt;
	    
	    private String texte_en;
	    
	    public String getTexte_en() {
			return texte_en;
		}

		public void setTexte_en(String texte_en) {
			this.texte_en = texte_en;
		}

		public String getMots_cles_en() {
			return mots_cles_en;
		}

		public void setMots_cles_en(String mots_cles_en) {
			this.mots_cles_en = mots_cles_en;
		}

		public String getTextecourt_en() {
			return textecourt_en;
		}

		public void setTextecourt_en(String textecourt_en) {
			this.textecourt_en = textecourt_en;
		}

		private String mots_cles_en;
	    private String textecourt_en;
	    
	    private float localisation_x;
	    
	    private float localisation_y;
	    
	    public String getTextecourt() {
			return textecourt;
		}

		public void setTextecourt(String textecourt) {
			this.textecourt = textecourt;
		}

		public String getMots_cles() {
			return mots_cles;
		}

		public void setMots_cles(String mots_cles) {
			this.mots_cles = mots_cles;
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

		public int getCategoriehistoire() {
			return categoriehistoire;
		}

		public void setCategoriehistoire(int categoriehistoire) {
			this.categoriehistoire = categoriehistoire;
		}

		private int categoriehistoire;
}
