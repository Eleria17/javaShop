package org.eleria.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="commandes")
public class commandes {
	
	  @Id
	    @Column(name="ID")
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	  
	    public Integer getBoutique() {
		return boutique;
	}

	public void setBoutique(Integer boutique) {
		this.boutique = boutique;
	}

		private Integer utilisateur;
	    private Integer boutique;
	    private float montant_total_HT;
	    private float fraislivraison;
	    
	    public float getFraislivraison() {
			return fraislivraison;
		}

		public void setFraislivraison(float fraislivraison) {
			this.fraislivraison = fraislivraison;
		}

		private Integer nbre_article;
	    private Integer status;
	    public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		private Date dateCommande;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Integer getUtilisateur() {
			return utilisateur;
		}

		public void setUtilisateur(Integer utilisateur) {
			this.utilisateur = utilisateur;
		}

		public float getMontant_total_HT() {
			return montant_total_HT;
		}

		public void setMontant_total_HT(float montant_total_HT) {
			this.montant_total_HT = montant_total_HT;
		}

		public Integer getNbre_article() {
			return nbre_article;
		}

		public void setNbre_article(Integer nbre_article) {
			this.nbre_article = nbre_article;
		}

		public Date getDateCommande() {
			return dateCommande;
		}

		public void setDateCommande(Date dateCommande) {
			this.dateCommande = dateCommande;
		}
}
