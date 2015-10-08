package org.eleria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="lienpagecategorie")
public class lienpagecategorie {
	
	  @Id
	    @Column(name="ID")
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	  
	    private int categorie;
	     
	  
	    public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		public int getCategorie() {
			return categorie;
		}
		public void setCategorie(int categorie) {
			this.categorie = categorie;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}

		private int type;
	    private int page;
}