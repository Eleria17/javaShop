package org.eleria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="commentaire")
public class commentaire {
	
	  @Id
	    @Column(name="ID")
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	  
	    public int getId() {
		return id;
	}
	    public  commentaire()
	    {
	    	
	    }
	    public  commentaire(int id, int type, int note,String texte, String nom, String email)
	    {
	    	this.parent=id;
	    	this.type=type;
	    	this.note=note;
	    	this.texte= texte;
	    	this.nom= nom;
	    	this.email=email;
	    }
	public void setId(int id) {
		this.id = id;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getNote() {
		return note;
	}
	public void setNote(Integer note) {
		this.note = note;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
		private Integer parent;
	     
	    
	    private Integer type;
	    private Integer note;
	    private String texte;
	    private String email;
	    private String nom;

		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
}
