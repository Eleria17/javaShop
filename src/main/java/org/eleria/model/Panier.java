package org.eleria.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;








import javax.persistence.FetchType;
import javax.persistence.OneToMany;

public class Panier {
	Integer Id;
	Integer utilisateur;
	
	@OneToMany(fetch=FetchType.LAZY)
	List<Integer> articles;
	@OneToMany(fetch=FetchType.LAZY)
	List<Integer> quantites;
	float fraisPort;
	float poids;
	public float getPoids() {
		return poids;
	}

	public void setPoids(float poids) {
		this.poids = poids;
	}

	public float getFraisPort() {
		return fraisPort;
	}

	public void setFraisPort(float fraisPort) {
		this.fraisPort = fraisPort;
	}

	float totalHt;
	public List<article> getObjArticles() {
		return objArticles;
	}

	public void setObjArticles(List<article> objArticles) {
		this.objArticles = objArticles;
	}

	List<article> objArticles;
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Integer utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Integer> getArticles() {
		return articles;
	}

	public void setArticles(List<Integer> articles) {
		this.articles = articles;
	}

	public List<Integer> getQuantites() {
		return quantites;
	}

	public void setQuantites(List<Integer> quantites) {
		this.quantites = quantites;
	}

	public float getTotalHt() {
		return totalHt;
	}

	public void setTotalHt(float totalHt) {
		this.totalHt = totalHt;
	}

	public Panier()
	{
		articles=new ArrayList<Integer>();
		quantites=new ArrayList<Integer>();
		objArticles=new ArrayList<article>();
		poids=0f;
		fraisPort=0f;
	}
	public void supPanier(int id)
	{
		article art=objArticles.get(id);
		Integer qt=quantites.get(id);
		articles.remove(id);
		objArticles.remove(id);
		quantites.remove(id);
		poids-=(art.getPoids()*qt);
		totalHt-=qt*art.getPrix();	
		BigDecimal value = new BigDecimal(totalHt);
		value = value.setScale(2, RoundingMode.HALF_EVEN); 
		totalHt=value.floatValue();
		if(poids<30000) fraisPort=24.50f;
		if(poids<10000) fraisPort=16.50f;
		if(poids<5000) fraisPort=11.50f;
		if(poids<2000) fraisPort=8.95f;
		if(poids<1000) fraisPort=7.9f;
		if(poids<750) fraisPort=7.25f;
		if(poids<500) fraisPort=6.45f;
		if(poids<250) fraisPort=5.50f;
	}
	public void ajoutPanier(int article, int quantite, article art)
	{
		articles.add(article);
		objArticles.add(art);
		quantites.add(quantite);
		
		totalHt+=quantite*art.getPrix();
		BigDecimal value = new BigDecimal(totalHt);
		value = value.setScale(2, RoundingMode.HALF_EVEN); 
		totalHt=value.floatValue();
		poids+=(art.getPoids()*quantite);
		if(poids<30000) fraisPort=24.50f;
		if(poids<10000) fraisPort=16.50f;
		if(poids<5000) fraisPort=11.50f;
		if(poids<2000) fraisPort=8.95f;
		if(poids<1000) fraisPort=7.9f;
		if(poids<750) fraisPort=7.25f;
		if(poids<500) fraisPort=6.45f;
		if(poids<250) fraisPort=5.50f;		
	}
}
