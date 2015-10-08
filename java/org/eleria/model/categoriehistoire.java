package org.eleria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categoriehistoire")
public class categoriehistoire {
	
	  @Id
	    @Column(name="ID")
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	  
	    private String nom;
	    private String texte;
	    private int parent;
	    private String nom_en;
	    private String texte_en;
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
		public int getParent() {
			return parent;
		}
		public void setParent(int parent) {
			this.parent = parent;
		}
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
	  
	 
}