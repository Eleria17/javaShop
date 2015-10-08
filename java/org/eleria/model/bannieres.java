package org.eleria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="bannieres")
public class bannieres {
	
	  @Id
	    @Column(name="ID")
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	  
	    private String fichier;
	     
	  
	    public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getFichier() {
			return fichier;
		}
		public void setFichier(String fichier) {
			this.fichier = fichier;
		}
		public int getClient() {
			return client;
		}
		public void setClient(int client) {
			this.client = client;
		}
		public int getPosition() {
			return position;
		}
		public void setPosition(int position) {
			this.position = position;
		}
		private int client;
	    private int position;
	    private String lien;


		public String getLien() {
			return lien;
		}
		public void setLien(String lien) {
			this.lien = lien;
		}
	    
}