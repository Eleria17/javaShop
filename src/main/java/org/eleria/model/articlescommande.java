package org.eleria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="articlescommande")
public class articlescommande {
	
	  @Id
	    @Column(name="ID")
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	    private Integer boutique;
		public Integer getBoutique() {
			return boutique;
		}
		public void setBoutique(Integer boutique) {
			this.boutique = boutique;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		private Integer commande;
		private Integer quantite;
		private Integer utilisateur;
		private Integer article;
		public Integer getArticle() {
			return article;
		}
		public void setArticle(Integer article) {
			this.article = article;
		}
		public Integer getCommande() {
			return commande;
		}
		public void setCommande(Integer commande) {
			this.commande = commande;
		}
		public Integer getQuantite() {
			return quantite;
		}
		public void setQuantite(Integer quantite) {
			this.quantite = quantite;
		}
		public Integer getUtilisateur() {
			return utilisateur;
		}
		public void setUtilisateur(Integer utilisateur) {
			this.utilisateur = utilisateur;
		}
}
