package org.eleria.dao;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.eleria.model.*;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class indikoDAOImpl implements indikoDAO{
	@Autowired
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     
    @Override
    @Transactional
    public void save(pagehistoire p) {
    	sessionFactory.getCurrentSession().saveOrUpdate(p);
    }
    @Override
    @Transactional
    public void save(utilisateurs p) {
    	Session session = this.sessionFactory.openSession();
    	session.saveOrUpdate(p);
        session.close();
    }
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<lienpagecategorie> getLienPCategorie() {
    	return  sessionFactory.getCurrentSession().createQuery("from lienpagecategorie").list();
    }
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<categorieboutique> getCategorie() {
    	return  sessionFactory.getCurrentSession().createQuery("from categorieboutique").list();
    }
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public categorieboutique getCategorieBoutique(Integer id) {
    	List<categorieboutique>list= sessionFactory.getCurrentSession().createQuery("from categorieboutique where id="+id).list();
    	
    	 
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }      
        return null;
    }
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<categoriehistoire>getCategorieHistoire() {
    	return  sessionFactory.getCurrentSession().createQuery("from categoriehistoire").list();
    }
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public categoriehistoire getCategorieHistoire(Integer id) {
    	List<categoriehistoire> list= sessionFactory.getCurrentSession().createQuery("from categoriehistoire where id="+id).list();

   	 
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }      
        return null;
    }
    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<commentaire> getCommentairesByPage(Integer id)
    {
    	 List<commentaire> personList = sessionFactory.getCurrentSession().createQuery("from commentaire  where type=1 AND parent="+id).list();
         return personList;
    }
    @Override
    @Transactional
    public Integer getNoteByPage(Integer id, Integer type)
    {
    	 List Lmoyenne = sessionFactory.getCurrentSession().createQuery("select avg(note) from commentaire  where type="+type+" AND parent="+id).list();
         Integer moyenne=4;
         if(Lmoyenne!=null)
         {
        	 if (Lmoyenne.size()>0) 
        	 {
        		 Double tmp=(Double)Lmoyenne.get(0);
        		 if(tmp!=null)
        			 moyenne=new Integer((int) Math.round(tmp));
        	 }
         }
         return moyenne;
    }
    @Override
    @Transactional
    public void save(commentaire p) {
    	Session session = this.sessionFactory.openSession();
    	session.saveOrUpdate(p);
        session.close();
    }
    @Override
    @Transactional
    public void save(Panier p) {
    	Session session = this.sessionFactory.openSession();
    	commandes cmd=new commandes();
    	cmd.setDateCommande(new Date(new java.util.Date().getTime()));
    	cmd.setUtilisateur(p.getUtilisateur());
    	cmd.setMontant_total_HT(p.getTotalHt());
    	cmd.setNbre_article(p.getArticles().size());
    	cmd.setStatus(0);
    	session.save(cmd);
    	int indice=0;
    	int boutique=0;
    	for (int tmp : p.getArticles())
    	{
    		articlescommande art=new articlescommande();
    		art.setCommande(cmd.getId());
    		art.setUtilisateur(p.getUtilisateur());
    		art.setQuantite(p.getQuantites().get(indice));
    		art.setArticle(tmp);article article=getArticle(tmp);
    		boutique=article.getBoutique();
    		art.setBoutique(boutique);
    		session.save(art);
    		
    		article.setQuantiteDispo(article.getQuantiteDispo()-1);
    		// ajouter notification quand c'est � zero
    		//session.merge(article);
    		session.update(article);
    		indice++;
    	}
    	cmd.setBoutique(boutique);
    	session.save(cmd);
        session.close();
    }
    @Override
    @Transactional
    public utilisateurs login(String login, String password) {
   	 String hql = "from utilisateurs where login='" + login+"' and motDePasse='"+password+"'";
     Query query = sessionFactory.getCurrentSession().createQuery(hql);      
     @SuppressWarnings("unchecked")
     List<utilisateurs> listBoutiques = query.list();
      
     if (listBoutiques != null && !listBoutiques.isEmpty()) {
         return listBoutiques.get(0);
     }      
     return null;
    }
    
    @Override
    @Transactional
	 public pagehistoire getPagehistoireById(int id)
	 {
		 String hql = "from pagehistoire where ID=" + id;
	     Query query = sessionFactory.getCurrentSession().createQuery(hql);      
	     @SuppressWarnings("unchecked")
	     List<pagehistoire> listUser = query.list();
	      
	     if (listUser != null && !listUser.isEmpty()) {
	         return listUser.get(0);
	     }      
	     return null;	 
	 }
    @Override
    @Transactional
	 public zone getZoneById(int id)
	 {
		 String hql = "from zone where ID=" + id;
	     Query query = sessionFactory.getCurrentSession().createQuery(hql);      
	     @SuppressWarnings("unchecked")
	     List<zone> listUser = query.list();
	      
	     if (listUser != null && !listUser.isEmpty()) {
	         return listUser.get(0);
	     }      
	     return null;	 
	 }
    
    @Override
    @Transactional
	 public List<elementcarte> getCarteZoneById(int id)
	 {
		  List<pagehistoire> histoList = sessionFactory.getCurrentSession().createQuery("from pagehistoire where zone="+id+" order by position").list();

		List<boutiques> boutiList = sessionFactory.getCurrentSession().createQuery("from boutiques	where zone="+id).list();
	
	     List<elementcarte> listElt = new ArrayList<elementcarte>();
	     for (pagehistoire tmp : histoList)
	    {
	    	 elementcarte elt=new elementcarte();
	    	 elt.setLocal_x(tmp.getLocalisation_x());
	    	 elt.setLocal_y(tmp.getLocalisation_y());
	    	 elt.setNom(tmp.getNom());
	    	 elt.setTexte(tmp.getTexte());
	    	 elt.setUrl("lieu?id="+tmp.getId());
	    	 listElt.add(elt);	    	 
	    }
	     for (boutiques tmp : boutiList)
		    {
		    	 elementcarte elt=new elementcarte();
		    	 elt.setLocal_x(tmp.getLocalisation_x());
		    	 elt.setLocal_y(tmp.getLocalisation_y());
		    	 elt.setNom(tmp.getNom());
		    	 elt.setTexte(tmp.getTexte());
		    	 elt.setUrl("boutique?id="+tmp.getId());
		    	 listElt.add(elt);	    	 
		    }
	     return listElt;	 
	 }
    
    @Override
    @Transactional
    public List<bannieres> getPublicites()
    {
    	List<bannieres> pub = sessionFactory.getCurrentSession().createQuery("from bannieres  order by rand()").setMaxResults(4).list();
        return pub;    	
    }
    @Override
    @Transactional
    public List<bannieres> getAllPublicites()
    {
    	List<bannieres> pub = sessionFactory.getCurrentSession().createQuery("from bannieres  order by rand()").list();
        return pub;    	
    }
    @Override
    @Transactional
	 public boutiques getBoutiqueById(int id)
	 {
		 String hql = "from boutiques where ID=" + id;
	     Query query = sessionFactory.getCurrentSession().createQuery(hql);      
	     @SuppressWarnings("unchecked")
	     List<boutiques> listBoutiques = query.list();
	      
	     if (listBoutiques != null && !listBoutiques.isEmpty()) {
	         return listBoutiques.get(0);
	     }      
	     return null;	 
	 }
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<pagehistoire> list() {        
        List<pagehistoire> personList = sessionFactory.getCurrentSession().createQuery("from pagehistoire order by position").list();
        return personList;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<zone> getListeZones()
    {
    	 List<zone> personList = sessionFactory.getCurrentSession().createQuery("from zone").list();
         return personList;
    }
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<pagehistoire>listHistoireByCat(int id) {        
        List<pagehistoire> personList = sessionFactory.getCurrentSession().createQuery("from pagehistoire where categoriehistoire="+id+" order by position").list();
      	List<lienpagecategorie>  list= sessionFactory.getCurrentSession().createQuery("from lienpagecategorie  where type=1 and categorie="+id).list();
        for(lienpagecategorie page:list)
        {
        	personList.add(getPagehistoireById(page.getPage()));
        }
        List<pagehistoire> personList2 = sessionFactory.getCurrentSession().createQuery("select foo   from pagehistoire foo, categoriehistoire bar   where foo.categoriehistoire = bar.id and bar.parent="+id+" order by foo.position").list();
        personList.addAll(personList2);
        return personList;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<pagehistoire> listHistoireByZone(int id) {        
        List<pagehistoire> personList = sessionFactory.getCurrentSession().createQuery("from pagehistoire where zone="+id+" order by position").list();
        return personList;
    }
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
 public List<boutiques>listBoutiquesByZone(int id) { 
	List<boutiques> personList = sessionFactory.getCurrentSession().createQuery("from boutiques	where zone="+id).list();
     return personList;     
    }
 @SuppressWarnings("unchecked")
 @Override
 @Transactional
 public List<pagehistoire> listHistoireBySearch(String terme) {        
     List<pagehistoire> personList = sessionFactory.getCurrentSession().createQuery("from pagehistoire where texte like '%"+terme+"%' or nom like '%"+terme+"%'").list();
     return personList;
 }
 @SuppressWarnings("unchecked")
 @Override
 @Transactional
public List<boutiques>listBoutiquesBySearch(String terme) { 
	List<boutiques> personList = sessionFactory.getCurrentSession().createQuery("from boutiques	where texte like '%"+terme+"%' or nom like '%"+terme+"%'").list();
  return personList;     
 }
    @Override
    @Transactional
 public boutiques getPageBoutiqueById(int id)
 {
	 String hql = "from boutiques where ID=" + id;
     Query query = sessionFactory.getCurrentSession().createQuery(hql);
      
     @SuppressWarnings("unchecked")
     List<boutiques> listUser = query.list();
      
     if (listUser != null && !listUser.isEmpty()) {
         return listUser.get(0);
     }      
     return null;	 
 }
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<boutiques> listBoutiques() {        
        List<boutiques> personList = sessionFactory.getCurrentSession().createQuery("from boutiques	").list();
        return personList;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<boutiques> listBoutiquesByCat(int id) {        
        List<boutiques> personList = sessionFactory.getCurrentSession().createQuery("from boutiques where categorie="+id).list();
    	List<lienpagecategorie>  list= sessionFactory.getCurrentSession().createQuery("from lienpagecategorie  where type=2 and categorie="+id).list();
        for(lienpagecategorie page:list)
        {
        	personList.add(getBoutiqueById(page.getPage()));
        }
        
        List<boutiques> personList2 = sessionFactory.getCurrentSession().createQuery("select foo   from boutiques foo, categorieboutique bar   where foo.categorie = bar.id and bar.parent="+id).list();
        personList.addAll(personList2);
        return personList;
    }
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<article> getSuggestionArt(int id)
    {
   	 String hql = "from article  order by rand()";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<article> listArticle = query.setMaxResults(3).list();
             
        return listArticle;	 
    }
    @Override
    @Transactional
    @SuppressWarnings("unchecked")
 public List<article> getArticlesByBoutique(int id)
 {
	 String hql = "from article where boutique=" + id;
     Query query = sessionFactory.getCurrentSession().createQuery(hql);
      
     List<article> listArticle = query.list();
          
     return listArticle;	 
 }
 @SuppressWarnings("unchecked")
 @Override
 @Transactional
 public article getArticle(int id) {
 	 String hql = "from article where ID=" + id;
      Query query = sessionFactory.getCurrentSession().createQuery(hql);
       
      @SuppressWarnings("unchecked")
      List<article> listUser = query.list();
       
      if (listUser != null && !listUser.isEmpty()) {
          return listUser.get(0);
      }          
      return null;    	
 }
 
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public article getArticle() {
    	 String hql = "from article";// where ID=" + id;
         Query query = sessionFactory.getCurrentSession().createQuery(hql);
          
         @SuppressWarnings("unchecked")
         List<article> listUser = query.list();
          
         if (listUser != null && !listUser.isEmpty()) {
             return listUser.get(0);
         }          
         return null;    	
    }
}
