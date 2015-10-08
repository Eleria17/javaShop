package org.eleria.model;




public class elementcarte {	
	 
	    private int id;
	  
	    private String nom;
	    private String texte;
	    private String url;
	    public String getTexte() {
			return texte;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public void setTexte(String texte) {
			this.texte = texte;
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
	
		private float local_x;
	    private float local_y;


		public float getLocal_x() {
			return local_x;
		}
		public void setLocal_x(float local_x) {
			this.local_x = local_x;
		}
		public float getLocal_y() {
			return local_y;
		}
		public void setLocal_y(float local_y) {
			this.local_y = local_y;
		}
}