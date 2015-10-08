package org.eleria.dao;
import java.util.List;

import org.eleria.model.*;

public interface indikoDAO {
	 public void save(pagehistoire p);
     
	    public List<pagehistoire> list();
	    public List<boutiques> listBoutiques();
	    public pagehistoire getPagehistoireById(int id);
	    public boutiques getBoutiqueById(int id);
	    public article getArticle(int id);public article getArticle();
	    public List<article>  getArticlesByBoutique(int id);
	    public boutiques getPageBoutiqueById(int id);
	   public List<bannieres> getPublicites(); public void save(utilisateurs p) ;
	   public utilisateurs login(String login, String password);
	   public void save(commentaire p);
	void save(Panier p);

	zone getZoneById(int id);

	List<boutiques> listBoutiquesByZone(int id);

	List<pagehistoire> listHistoireByZone(int id);

	List<commentaire> getCommentairesByPage(Integer id);

	Integer getNoteByPage(Integer id, Integer type);

	List<pagehistoire> listHistoireBySearch(String terme);

	List<boutiques> listBoutiquesBySearch(String terme);

	List<categorieboutique> getCategorie();

	List<categoriehistoire> getCategorieHistoire();

	List<elementcarte> getCarteZoneById(int id);

	List<boutiques> listBoutiquesByCat(int id);

	List<pagehistoire> listHistoireByCat(int id);

	List<zone> getListeZones();

	List<lienpagecategorie> getLienPCategorie();

	categoriehistoire getCategorieHistoire(Integer id);

	categorieboutique getCategorieBoutique(Integer id);

	List<bannieres> getAllPublicites();

	List<article> getSuggestionArt(int id);
}
