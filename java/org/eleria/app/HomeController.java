package org.eleria.app;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.eleria.model.*;
import org.eleria.dao.*;

/**
 * Controleur central de l'application
 */
@Controller
@SessionAttributes("panier")
public class HomeController {
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	Panier panier;
	/*
	@Autowired
	private MailSender mailSender;*/

	public HomeController() {
		panier = new Panier();
	}
	// Fonction commune à tous les view Models
	private Model factor(Model model, Locale locale)
	{
		ApplicationContext tradContext = new ClassPathXmlApplicationContext(
				"i18n/javashopContext.xml");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(	"hibernate.xml");
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		model.addAttribute("Utilisateurs", new utilisateurs());
		model.addAttribute("publicites", personDAO.getPublicites());
		model.addAttribute("Culture", "Culture");
		model.addAttribute("Sortir", "Agenda");
		model.addAttribute("Service", "Service");
		model.addAttribute("Shopping", "Commerce");
		model.addAttribute("phraseBP",context.getMessage("patron.phraseBP",null, Locale.FRENCH));
		List<categorieboutique> catL = personDAO.getCategorie();
		List<categoriehistoire> catH =personDAO.getCategorieHistoire();		
		if (locale.getLanguage().equals(new Locale("en").getLanguage()))
		{
			model.addAttribute("Sortir", "Go out");
			for(categorieboutique cat: catL )
			{	
				cat.setNom(cat.getNom_en());
				cat.setTexte(cat.getTexte_en());				
			}
			for(categoriehistoire cat: catH )
			{	
				cat.setNom(cat.getNom_en());
				cat.setTexte(cat.getTexte_en());
			}			
		}
		model.addAttribute("categorie", catL);	
		model.addAttribute("categorieHistoire", catH);
		context.close();
		return model;
	}
	@RequestMapping(value = "MonCompte", method = RequestMethod.GET)
	public String monCompte(Model model, Locale locale,	@RequestParam(value = "lang", required = false) String lang) {
		model=factor(model,locale);
		return "MonCompte";
	}
	@RequestMapping(value = "recherche", method = RequestMethod.POST)
	public String recherche(@RequestParam( value="terme",  required = false) String terme,Model model, Locale locale,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(	"hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);		
		List<pagehistoire> pageH=personDAO.listHistoireBySearch(terme);
		List<boutiques> listB=personDAO.listBoutiquesBySearch(terme);
		if (locale.getLanguage().equals(new Locale("en").getLanguage()))
		{
			for(pagehistoire cat: pageH )
			{
				cat.setMots_cles(cat.getMots_cles_en());
				cat.setNom(cat.getNom_en());
				cat.setTexte(cat.getTexte_en());
				cat.setTexte(cat.getTexte_en());
			}
			for(boutiques cat: listB )
			{
				cat.setNom(cat.getNom_en());
				cat.setTexte(cat.getTexte_en());
				cat.setTexte(cat.getTexte_en());
			}
		}
		model.addAttribute("listeHistoire", pageH);
		model.addAttribute("listeBoutiques", listB);
		model.addAttribute("terme", terme);
		context.close();
		if(lang=="en")
			Locale.setDefault(Locale.ENGLISH);
		return "recherche";
	}
	// Page apres un clic sur la carte de la page d'accueil
	@RequestMapping(value = "zone", method = RequestMethod.GET)
	public String zone(@RequestParam( value="id",  required = false) int id, HttpServletRequest httpServletRequest,	Model model, @RequestParam(value = "page", defaultValue="0", required=false) int page,Locale locale,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		List<pagehistoire> pageH=personDAO.listHistoireByZone(id);
		List<boutiques> listB=personDAO.listBoutiquesByZone(id);
		zone lazone=personDAO.getZoneById(id);
		if (locale.getLanguage().equals(new Locale("en").getLanguage()))
		{
			lazone.setNom(lazone.getNom_en());
			lazone.setTexte(lazone.getTexte_en());
			for(pagehistoire cat: pageH )
			{
				cat.setMots_cles(cat.getMots_cles_en());
				cat.setNom(cat.getNom_en());
				cat.setTexte(cat.getTexte_en());
				cat.setTexte(cat.getTexte_en());
			}
			for(boutiques cat: listB )
			{
				cat.setNom(cat.getNom_en());
				cat.setTexte(cat.getTexte_en());
				cat.setTexte(cat.getTexte_en());
			}
		}
		PagedListHolder<pagehistoire> pagedListHolder = new PagedListHolder<pagehistoire>(pageH);
		pagedListHolder.setPage(page);
		int pageSize =9;
		pagedListHolder.setPageSize(pageSize);
		model.addAttribute("listeHistoire", pagedListHolder);
		PagedListHolder<boutiques> pagedListHolder2 = new PagedListHolder<boutiques> (listB);
		pagedListHolder2.setPage(page);
		pagedListHolder2.setPageSize(pageSize);
		model.addAttribute("listeBoutiques", pagedListHolder2);pagedListHolder2.setPageSize(6);		
		model.addAttribute("zone",  lazone);
		model.addAttribute("ID", id);
		model.addAttribute("eltCart", personDAO.getCarteZoneById(id));
		model.addAttribute("gallerie", gallerie(4, id, httpServletRequest.getSession().getServletContext().getRealPath("/")));	
		context.close();		
		if(lang=="en")
			Locale.setDefault(Locale.ENGLISH);		
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "zone";
	}
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(@RequestParam( value="username",  required = false) String login,@RequestParam( value="password",  required = false) String password,Model model, Locale locale,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(	"hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		utilisateurs util=personDAO.login(login, password);
		if(util==null) {context.close();return "panier";}
		panier.setUtilisateur(util.getId());
       // montant = montant.Replace(",", ".");
		
        model.addAttribute("montant", String.format(Locale.ENGLISH,"%.2f",panier.getTotalHt()));
        model.addAttribute("businessKey","sylvain.roux@idk-multimedia.fr");
        model.addAttribute("businessKey","sylvain.roux@idk-multimedia.fr");
        model.addAttribute("UseSandbox","true");
   /*     if(!false)
        	model.addAttribute("actionURL","https://www.sandbox.paypal.com/cgi-bin/webscr");
        else*/
        	model.addAttribute("actionURL","https://www.paypal.com/cgi-bin/webscr");
        model.addAttribute("panier", panier);
        model.addAttribute("publicites", personDAO.getPublicites());
        if(util.getCode_postal().startsWith("17")) model.addAttribute("charenteMaritime", true); else model.addAttribute("charenteMaritime", false);
        context.close();
        if(lang=="en")
			Locale.setDefault(Locale.ENGLISH);
		return "validationCommande";
	}
	@RequestMapping(value = "MonCompte", method = RequestMethod.POST)
	public String monCompte( @ModelAttribute("Utilisateurs") utilisateurs data,Model model, Locale locale,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(	"hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);;model.addAttribute("publicites", personDAO.getPublicites());
		personDAO.save(data);
		panier.setUtilisateur(data.getId());
       // montant = montant.Replace(",", ".");
		model.addAttribute("montant", String.format(Locale.ENGLISH,"%.2f",panier.getTotalHt()));
        model.addAttribute("businessKey","sylvain.roux@idk-multimedia.fr");
        model.addAttribute("publicites", personDAO.getPublicites());
      /*  model.addAttribute("UseSandbox","true");
        if(!false)
        	model.addAttribute("actionURL","https://www.sandbox.paypal.com/cgi-bin/webscr");
        else*/
        	model.addAttribute("actionURL","https://www.paypal.com/cgi-bin/webscr");
        model.addAttribute("panier", panier);model.addAttribute("publicites", personDAO.getPublicites());
        if(data.getCode_postal().startsWith("17")) model.addAttribute("charenteMaritime", true); else model.addAttribute("charenteMaritime", false);
		context.close();
		if(lang=="en")
			Locale.setDefault(Locale.ENGLISH);
		return "validationCommande";
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String accueil(Locale locale, Model model,	@RequestParam(value = "lang", required = false) String lang) {		
		model=factor(model, locale);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"hibernate.xml");
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		List<zone> lZone=personDAO.getListeZones();
		for(zone cat: lZone )
		{
			cat.setTexte(HtmlUtils.htmlUnescape(cat.getTexte()));
		}
		if (locale.getLanguage().equals(new Locale("en").getLanguage()))
		{
			for(zone cat: lZone )
			{
				cat.setNom(cat.getNom_en());
				cat.setTexte(cat.getTexte_en());
			}
		}
		model.addAttribute("listeZone", lZone);
		if(lang=="en")
			Locale.setDefault(Locale.ENGLISH);
		context.close();
		return "accueil";
	}

	// ajout d'un article et de sa quantité dans la commande de l'utilisateur
	@RequestMapping(value = "/ajoutPanier", method = RequestMethod.GET)
	public String ajoutPanier(	Locale locale,	Model model,
			@RequestParam(value = "idArticle", required = false) Integer idArticle,		@RequestParam(value = "quantite", required = false) Integer quantite,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		panier.ajoutPanier(idArticle, quantite, personDAO.getArticle(idArticle));
		model.addAttribute("panier", panier);;model.addAttribute("publicites", personDAO.getPublicites());
		context.close();
		if(lang=="en")
			Locale.setDefault(Locale.ENGLISH);
		return "panier";
	}
	@RequestMapping(value = "/supPanier", method = RequestMethod.GET)
	public String supPanier(Locale locale,	Model model,
			@RequestParam(value = "id", required = false) Integer id,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		panier.supPanier(id);
		model.addAttribute("panier", panier);;model.addAttribute("publicites", personDAO.getPublicites());
		context.close();
		if(lang=="en")
			Locale.setDefault(Locale.ENGLISH);
		return "panier";
	}
	@RequestMapping(value = "/validationCommande", method = RequestMethod.POST)
	public String validationCommande(Locale locale, Model model,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		personDAO.save(panier);
		model.addAttribute("panier", panier);model.addAttribute("publicites", personDAO.getPublicites());
		model.addAttribute("paiement", true);
		model.addAttribute("messagePaiement", "Votre commande a bien été prise en compte. Merci.");
		context.close();
		if(lang=="en")
			Locale.setDefault(Locale.ENGLISH);
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();	 
			simpleMailMessage.setFrom("contact@indiko.fr");
			simpleMailMessage.setTo("m.khalfaoui@eleria.fr");
			simpleMailMessage.setSubject("Commande Indiko");
			simpleMailMessage.setText("commande Indiko de "+panier.getTotalHt()+" euros.");
			//mailSender.send(simpleMailMessage);	
		}catch(Exception ex) {
			
		}
		return "validationCommande";
	}
	@RequestMapping(value = "/validationTestCommande", method = RequestMethod.GET)
	public String validationCommande2(Locale locale, Model model,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		personDAO.save(panier);
		model.addAttribute("panier", panier);model.addAttribute("publicites", personDAO.getPublicites());
		model.addAttribute("paiement", true);
		model.addAttribute("messagePaiement", "Votre commande a bien été prise en compte. Merci.");
		context.close();
		if(lang=="en")
			Locale.setDefault(Locale.ENGLISH);
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();	 
			simpleMailMessage.setFrom("contact@indiko.fr");
			simpleMailMessage.setTo("m.khalfaoui@eleria.fr");
			simpleMailMessage.setSubject("Commande Indiko");
			simpleMailMessage.setText("commande Indiko de "+panier.getTotalHt()+" euros.");
			//mailSender.send(simpleMailMessage);	
		}catch(Exception ex) {
			
		}
		return "validationCommande";
	}
	@RequestMapping(value = "/panier", method = RequestMethod.GET)
	public String panier(Locale locale, Model model,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		model.addAttribute("panier", panier);model.addAttribute("publicites", personDAO.getPublicites());
		context.close();
		if(lang=="en")
			Locale.setDefault(Locale.ENGLISH);
		return "panier";
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(Locale locale, Model model, @RequestParam(value = "page", defaultValue="0", required=false) int page,@RequestParam(value = "id", required = false) Integer id,	@RequestParam(value = "lang", required = false) String lang,HttpServletRequest request, HttpServletResponse response) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		List<pagehistoire> list;
		if(id!=null)
		 list = personDAO.listHistoireByCat(id);
		else
			 list = personDAO.list();		
		if (locale.getLanguage().equals(new Locale("en").getLanguage()))
		{
			for(pagehistoire cat: list )
			{
				cat.setMots_cles(cat.getMots_cles_en());
				cat.setNom(cat.getNom_en());
				cat.setTexte(cat.getTexte_en());
				cat.setTexte(cat.getTexte_en());
			}
		}		
		PagedListHolder<pagehistoire> pagedListHolder = new PagedListHolder<pagehistoire>(list);
		pagedListHolder.setPage(page);
		int pageSize =20;
		pagedListHolder.setPageSize(pageSize);
		model.addAttribute("listeHistoire", pagedListHolder);
		model.addAttribute("pagedListHolder", pagedListHolder);		
		model.addAttribute("publicites", personDAO.getPublicites());
		context.close();
		if(lang=="en")
			Locale.setDefault(Locale.ENGLISH);
		return "home";
	}
	private String[] gallerie(int type, Integer ID, String path)
	{
		  File dir = new File(path+"/resources/Images/culture/" + ID.toString() + "_gallerie/"); // current directory
		  if(type==2)
			  dir = new File(path+"/resources/Images/boutiques/" + ID.toString() + "_gallerie/"); 
		  if(type==3)
			  dir = new File(path+"/resources/Images/articles/" + ID.toString() + "_gallerie/"); 
		  if(type==4)
			  dir = new File(path+"/resources/Images/zone/" + ID.toString() + "_gallerie/"); 
	        return dir.list(new FilenameFilter() {
	            @Override
	            public boolean accept(File dir, String name) {
	                return name.toLowerCase().startsWith("");//param.idArticle
	            }
	        });
	}
	@RequestMapping(value = "lieu", method = RequestMethod.GET)
	public String lieu(Locale locale, Model model,
			@RequestParam(value = "id", required = false) Integer id, HttpServletRequest httpServletRequest,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		pagehistoire page = personDAO.getPagehistoireById(id);
		categoriehistoire cat=personDAO.getCategorieHistoire(page.getCategoriehistoire());		
		model.addAttribute("IDcategorieparent",cat.getParent());
		model.addAttribute("NomCategorie", cat.getNom());
		categoriehistoire catP=null;
		if(cat.getParent()>=0)
		{			
			catP=personDAO.getCategorieHistoire(cat.getParent());
			if(catP!=null)
			model.addAttribute("NomCategorieParent",catP.getNom());	
			else
				model.addAttribute("NomCategorieParent","Culture");
		}
		else
		{
			model.addAttribute("NomCategorieParent","Culture");
		}
		if (locale.getLanguage().equals(new Locale("en").getLanguage()))
		{
			page.setMots_cles(page.getMots_cles_en());
			page.setNom(page.getNom_en());
			page.setTexte(page.getTexte_en());
			page.setTexte(page.getTexte_en());
			if(catP!=null)
			model.addAttribute("NomCategorieParent",catP.getNom_en());
			model.addAttribute("NomCategorie", cat.getNom_en());
		}
		model.addAttribute("pageHistoire", page);
		model.addAttribute("publicites", personDAO.getPublicites());
		model.addAttribute("article", personDAO.getArticle());
		model.addAttribute("commentaires", personDAO.getCommentairesByPage(id));
		model.addAttribute("note", personDAO.getNoteByPage(id,2));
		model.addAttribute("gallerie", gallerie(1, id, httpServletRequest.getSession().getServletContext().getRealPath("/")));	
		context.close();
		return "lieu";
	}

	@RequestMapping(value = "boutique", method = RequestMethod.GET)
	public String boutique(Locale locale, Model model,
			@RequestParam(value = "id", required = false) Integer id, HttpServletRequest httpServletRequest) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		boutiques page = personDAO.getBoutiqueById(id);
		categorieboutique cat=personDAO.getCategorieBoutique(page.getCategorie());		
		model.addAttribute("IDcategorieparent",cat.getParent());
		model.addAttribute("NomCategorie", cat.getNom());
		model.addAttribute("boutiqueLigne","VOTRE BOUTIQUE	EN LIGNE");
		if(cat.getParent()>=0)
		{
			categorieboutique catP=personDAO.getCategorieBoutique(cat.getParent());
			if(catP!=null)
			model.addAttribute("NomCategorieParent",catP.getNom());	
			else
				model.addAttribute("NomCategorieParent","Shopping");
		}
		else
		{
			model.addAttribute("NomCategorieParent","Shopping");
		}
		if (locale.getLanguage().equals(new Locale("en").getLanguage()))
		{
			if(cat.getParent()>=0)
			{
				categorieboutique catP=personDAO.getCategorieBoutique(cat.getParent());
				model.addAttribute("NomCategorieParent",catP.getNom_en());	
			}
			else {
			model.addAttribute("NomCategorie", cat.getNom_en());
			}
			model.addAttribute("boutiqueLigne","Your online shop");
		}
		
		model.addAttribute("note", personDAO.getNoteByPage(id,3));
		model.addAttribute("boutique", page);
		model.addAttribute("publicites", personDAO.getPublicites());
		List<article> artl=personDAO.getArticlesByBoutique(id);		
		model.addAttribute("articles", artl);
		Map<Long,List<article>> hm=new HashMap<Long, List<article>>();
		model.addAttribute("gallerie", gallerie(2, id, httpServletRequest.getSession().getServletContext().getRealPath("/")));
		Map<Long,String[]> galArt=new HashMap<Long,String[]>();
		for (article x : artl)
		{		
			Long idt=(long)x.getId();
			hm.put(idt,personDAO.getSuggestionArt( x.getId()));
			//model.addAttribute("note_"+x.getId(), personDAO.getNoteByPage(x.getId(),4));
			galArt.put(idt,gallerie(3, x.getId(), httpServletRequest.getSession().getServletContext().getRealPath("/")));
		}		
		model.addAttribute("gallerieB", galArt);
		model.addAttribute("articleSug", hm);
		context.close();
		return "boutique";
	}

	@RequestMapping(value = "shopping", method = RequestMethod.GET)
	public String shopping(Locale locale, Model model,	@RequestParam(value = "id", required = false) Integer id,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		if(id==null) id=0;
		List<boutiques> list = personDAO.listBoutiquesByCat(id);
		if (locale.getLanguage().equals(new Locale("en").getLanguage()))
		{
			
		}
		model.addAttribute("publicites", personDAO.getPublicites());
		context.close();
		model.addAttribute("listeHistoire", list);
		return "shopping";
	}
	@RequestMapping(value = "bonPlan", method = RequestMethod.GET)
	public String bonPlan(Locale locale, Model model,	@RequestParam(value = "id", required = false) Integer id,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");
		model=factor(model, locale);
		indikoDAO personDAO = context.getBean(indikoDAO.class);
		model.addAttribute("listePub", personDAO.getAllPublicites());
		context.close();
		return "bonPlan";
	}
	@RequestMapping(value = "contact", method = RequestMethod.GET)
	public String contact(Locale locale, Model model,	@RequestParam(value = "id", required = false) Integer id,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");
		model=factor(model, locale);
		/*indikoDAO personDAO = context.getBean(indikoDAO.class);
		List<boutiques> list = personDAO.listBoutiques();
		model.addAttribute("publicites", personDAO.getPublicites());
		context.close();
		model.addAttribute("listeHistoire", list);*/
		return "contact";
	}
	@RequestMapping(value = "plan", method = RequestMethod.GET)
	public String plan(Locale locale, Model model,	@RequestParam(value = "id", required = false) Integer id,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");
		model=factor(model, locale);
		/*indikoDAO personDAO = context.getBean(indikoDAO.class);
		List<boutiques> list = personDAO.listBoutiques();
		model.addAttribute("publicites", personDAO.getPublicites());
		context.close();
		model.addAttribute("listeHistoire", list);*/
		return "plan";
	}
	@RequestMapping(value = "partenaires", method = RequestMethod.GET)
	public String partenaires(Locale locale, Model model,	@RequestParam(value = "id", required = false) Integer id,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");
		model=factor(model, locale);
		/*indikoDAO personDAO = context.getBean(indikoDAO.class);
		List<boutiques> list = personDAO.listBoutiques();
		model.addAttribute("publicites", personDAO.getPublicites());
		context.close();
		model.addAttribute("listeHistoire", list);*/
		return "partenaires";
	}
	@RequestMapping(value = "mention", method = RequestMethod.GET)
	public String mention(Locale locale, Model model,	@RequestParam(value = "id", required = false) Integer id,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");
		model=factor(model, locale);
		/*indikoDAO personDAO = context.getBean(indikoDAO.class);
		List<boutiques> list = personDAO.listBoutiques();
		model.addAttribute("publicites", personDAO.getPublicites());
		context.close();
		model.addAttribute("listeHistoire", list);*/
		return "mention";
	}
	@RequestMapping(value = "qui", method = RequestMethod.GET)
	public String qui(Locale locale, Model model,	@RequestParam(value = "id", required = false) Integer id,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");
		model=factor(model, locale);
		/*indikoDAO personDAO = context.getBean(indikoDAO.class);
		List<boutiques> list = personDAO.listBoutiques();
		model.addAttribute("publicites", personDAO.getPublicites());
		context.close();
		model.addAttribute("listeHistoire", list);*/
		return "qui";
	}
	@RequestMapping(value = "/contactForm", method = RequestMethod.GET)
    public String contactForm(Locale locale, Model model,
    		 @RequestParam(value = "objet") String objet,@RequestParam(value = "email") String email,@RequestParam(value = "message") String message,	@RequestParam(value = "lang", required = false) String lang) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");
		model=factor(model, locale);
		/*indikoDAO personDAO = context.getBean(indikoDAO.class);
		List<pagehistoire> list = personDAO.list();
		model.addAttribute("publicites", personDAO.getPublicites());
		context.close();
		model.addAttribute("listeHistoire", list);*/
		return "home";
    }
	@RequestMapping(value = "/creeCommentaire", method = RequestMethod.POST)
    public String creeCommentaire(Locale locale, Model model,@RequestParam(value = "pageId") int pageId
    		, @RequestParam(value = "name") String nom,@RequestParam(value = "email") String email,@RequestParam(value = "comment") String comment) {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");        
	model=factor(model, locale);
	indikoDAO personDAO = context.getBean(indikoDAO.class);;  
	personDAO.save(new commentaire(pageId,2, 0, comment, nom, email));
	pagehistoire page = personDAO.getPagehistoireById(pageId);
	model.addAttribute("pageHistoire", page);
	model.addAttribute("publicites", personDAO.getPublicites());
	model.addAttribute("article", personDAO.getArticle());
	model.addAttribute("commentaires", personDAO.getCommentairesByPage(pageId));
	  context.close();
        return "lieu";
    }
	@RequestMapping(value = "/commentaire", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> setNote(@RequestParam(value = "articleId") int articleId, @RequestParam(value = "type") int type,@RequestParam(value = "niveau") int niveau) {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate.xml");        

	indikoDAO personDAO = context.getBean(indikoDAO.class);
	personDAO.save(new commentaire(articleId,type, niveau, "", "", ""));
	  context.close();
        return null;
    }
}
