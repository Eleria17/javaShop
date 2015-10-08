var menuAffiche = false;
var notationMode = true;
/**
 * KHALFAOUI Mahdi - Eleria Conseil
 * Funciton principales coté client Javascript - projet Indiko
 */
$(function() {
	// gestion de la notation - étoiles
 notationMode = true;
	$('.star').hover(function(e) {
		if (!notationMode)
			return;
		if("noteCookie"+type+"_"+articleId in localStorage)
			return;
		$(this).attr("src", "resources/Images/starON.png");
		var id = e.target.id;
		switch (id) {
		case "star1":
			$("#star2").attr("src", "resources/Images/starOFF.png");
			$("#star3").attr("src", "resources/Images/starOFF.png");
			$("#star4").attr("src", "resources/Images/starOFF.png");
			$("#star5").attr("src", "resources/Images/starOFF.png");
			break;
		case "star2":
			$("#star1").attr("src", "resources/Images/starON.png");
			$("#star3").attr("src", "resources/Images/starOFF.png");
			$("#star4").attr("src", "resources/Images/starOFF.png");
			$("#star5").attr("src", "resources/Images/starOFF.png");
			break;
		case "star3":
			$("#star1").attr("src", "resources/Images/starON.png");
			$("#star2").attr("src", "resources/Images/starON.png");
			$("#star4").attr("src", "resources/Images/starOFF.png");
			$("#star5").attr("src", "resources/Images/starOFF.png");
			break;
		case "star4":
			$("#star1").attr("src", "resources/Images/starON.png");
			$("#star2").attr("src", "resources/Images/starON.png");
			$("#star3").attr("src", "resources/Images/starON.png");
			$("#star5").attr("src", "resources/Images/starOFF.png");
			break;
		case "star5":
			$("#star1").attr("src", "resources/Images/starON.png");
			$("#star2").attr("src", "resources/Images/starON.png");
			$("#star3").attr("src", "resources/Images/starON.png");
			$("#star4").attr("src", "resources/Images/starON.png");
			break;
		}
		event.preventDefault();
		event.stopPropagation();
	});
	
	var dernier = 0; // Sous menu
	$('.aMenuModeIndiko').hover(function(e) {
		if (dernier != this.id) {
			$('#mH' + dernierH).css('display', 'none');			
			$('#' + dernierH + " > a").removeClass('menuActif');
			$('#m' + dernier).css('display', 'none');			
			$('#' + dernier + " > a").removeClass('menuActif');
		}
		$('#m' + this.id).show();

		$("#" + this.id + " > a").addClass('menuActif');
		dernier = this.id;
	});
	var dernierH = 0; // Sous menu
	$('.aMenuModeIndikoH').hover(function(e) {
		if (dernierH != this.id) {
			$('#m' + dernier).css('display', 'none');			
			$('#' + dernier + " > a").removeClass('menuActif');
			$('#mH' + dernierH).css('display', 'none');			
			$('#' + dernierH + " > a").removeClass('menuActif');
		}
		$('#mH' + this.id).show();

		$("#" + this.id + " > a").addClass('menuActif');
		dernierH = this.id;
	});       
	  $('#menuHomeIndiko').mouseleave (function(e) {
	  	$('.menuModeIndiko').hide();
	 });
	// article entier
	
	if( ($("#ResumeLieuCourt").height()/ parseFloat($("#ResumeLieuCourt").css("font-size")))<8)
		{
			$('#suiteArticle').hide();
		}
	$('#suiteArticle').click(function(e) {
		$(".resumeLieu").css('overflow', 'visible');
		$(".resumeLieu").css('height', 'auto');
		$('#suiteArticle').hide();
		event.preventDefault();
		event.stopPropagation();
	});
	// derouler la saisie de commentaire
	$('#affichCommenter').click(function(e) {
		if ($("#commenterDiv").css('display') == 'none')
			$("#commenterDiv").show();
		else
			$("#commenterDiv").hide();
		event.preventDefault();
		event.stopPropagation();
	});

	$('.star').click(function(e) {
		if (!notationMode)
			return;		
		$(this).attr("src", "resources/Images/starON.png");
		var id = e.target.id;
		notationMode = false;
		var niveau = 0;
		switch (id) {
		case "star1":
			niveau = 1;
			break;
		case "star2":
			niveau = 2;
			break;
		case "star3":
			niveau = 3;
			break;
		case "star4":
			niveau = 4;
			break;
		case "star5":
			niveau = 5;
			break;
		}
		if("noteCookie"+type+"_"+articleId in localStorage)
			return;
		localStorage.setItem("noteCookie"+type+"_"+articleId,niveau);
		$.getJSON('commentaire', {
			articleId : articleId,
			type : type,
			niveau : niveau
		}, function(data) {
		});
		event.preventDefault();
		event.stopPropagation();
	});

	var menu_ul = $('.menuIndiko > li > ul'), // Menu déroulant
	menu_a = $('.menuIndiko > li > a');
	menu_ul.hide();
	menu_a.click(function(e) {
		e.preventDefault();
		if (!$(this).hasClass('active')) {
			menu_a.removeClass('active');
			menu_ul.filter(':visible').slideUp('normal');
			$(this).addClass('active').next().stop(true, true).slideDown(
					'normal');
		} else {
			$(this).removeClass('active');
			$(this).next().stop(true, true).slideUp('normal');
		}
	});
	$('#flecheMenu')
			.click(
					function(e) {
						if (!menuAffiche) {
							$(".menuIndiko > * [style$='display: none;']")
									.show(	"slow",	function() {
												$("#menuHomeIndiko").addClass(
														"pleineHauteur");
												var hauteur;
												var body = document.body,
											    html = document.documentElement;

											var height = Math.max( body.scrollHeight, body.offsetHeight, 
											                       html.clientHeight, html.scrollHeight, html.offsetHeight, document.documentElement.clientHeight );
											hauteur = height;
											/*	if (typeof (window.innerHeight) == 'number')
													hauteur = height;
												else if (document.documentElement
														&& document.documentElement.clientHeight)
													hauteur = document.documentElement.clientHeight;*/
												document.getElementById("menuHomeIndiko").style.height = hauteur+ "px";
											});
							$("#flecheMenu").removeClass("flecheMenuBas");
							$("#flecheMenu").addClass("flecheMenuHaut");
							$('.menuModeIndiko').css('display', 'none');
						} else {
							$("#menuHomeIndiko").removeClass("pleineHauteur");
							document.getElementById("menuHomeIndiko").style.height = "auto";
							$(".menuIndiko > * [style$='display: block;']")
									.hide("slow", function() {});
							$("#flecheMenu").removeClass("flecheMenuHaut");
							$("#flecheMenu").addClass("flecheMenuBas");
						}
						menuAffiche = !menuAffiche;
					});

	//Lorsque vous cliquez sur un lien de la classe poplight
	$('a.poplight')	.on('click',	function() {
						var popID = $(this).data('rel'); //Trouver la pop-up correspondante
						var popWidth = $(document).width() * 90 / 100; //Trouver la largeur
						var popHeight = $(document).width() * 45 / 100;
						//Faire apparaitre la pop-up et ajouter le bouton de fermeture
						$('#' + popID)
								.fadeIn()
								.css({
									'width' : popWidth,
									'height' : popHeight
								}).prepend(
										'<a href="#" class="close"><img src="resources/Images/close_pop.png" class="btn_close" title="Close Window" alt="Close" /></a>');
						var popMargTop = ($('#' + popID).height() + 0) / 2;
						var popMargLeft = ($('#' + popID).width() + 90) / 2;
						$('#' + popID).css({
							'margin-top' : -popMargTop,
							'margin-left' : -popMargLeft
						});
						//Apparition du fond - .css({'filter' : 'alpha(opacity=80)'}) pour corriger les bogues d'anciennes versions de IE
						$('body').append('<div id="fade"></div>');
						$('#fade').css({
							'filter' : 'alpha(opacity=80)'
						}).fadeIn();
						return false;
					});
	$('body').on('click', 'a.close, #fade', function() { //Au clic sur le body...
		$('#fade , .popup_block').fadeOut(function() {
			$('#fade, a.close').remove();
		}); //...ils disparaissent ensemble		
		return false;
	});
	
	var dernierPopArt=0;
	$('.poplightArt')	.on('click',function() {
		var src = $(this).attr('src');
		var id=$(this).data('imgprin');
		var hauteur=$('#'+id).css( 'height');
		var largeur=$('#'+id).css( 'width');
		$('#'+id).attr('src', src);var hauteur2=$('#'+id).css( 'height');
		$('#'+id).css({ 'height' : hauteur});
		var ratio=((parseInt(hauteur)*parseInt(largeur))/parseInt(hauteur2))+"px";
		$('#'+id).css({ 'width' : ratio});// +
		return false;
	});
	$('body').on('click', 'a.closeArt, #fadeArt', function() { //Au clic sur le body...
		$('#fadeArt, .popup_blockArt').fadeOut(function() {
			$('#fadeArt, a.closeArt').remove();
		}); //...ils disparaissent ensemble		
		return false;
	});
	
	 $('.poplightBS').on('click',function(){
		   var src = $(this).attr('src');
		   var total = $('.poplightBS').length;// + 1; 
           var img = '<img src="' + src + '" class="img-responsive"/>';
           var index = $('.poplightBS').index(this); // La est le problème                  $(this).parent('div')
           var html = '';
           html += img;                
           html += '<div style="height:25px;clear:both;display:block;">';
           if(total>2)
           {
        	   if(index<total)
	           html += '<a class="controls next" href="'+ (index+1) + '">Suiv &raquo;</a>';
        	   else
        		   html += '<a class="controls next" style="display: none;" href="'+ (index+1) + '">Suiv &raquo;</a>'; 
	           if(index>0)
	           html += '<a class="controls previous" href="' + (index-1) + '">&laquo; Prec</a>';
	           else
	        	   html += '<a class="controls previous" style="display: none;"  href="' + index + '">&laquo; Prec</a>';
           }
           html += '</div>';
           $('#myModal').modal();
           $('#myModal').on('shown.bs.modal', function(){
               $('#myModal .modal-body').html(html);
           })
           $('#myModal').on('hidden.bs.modal', function(){
               $('#myModal .modal-body').html('');
           });     
    });  
	 $(document).on('click', 'a.controls', function(){
         var index = $(this).attr('href');
         var elemt = $('.imageGallerie:nth-child('+ index +') img');             
         var src = elemt.attr('src');
         $('.modal-body img').attr('src', src);         
         var newPrevIndex = parseInt(index) - 1; 
         var newNextIndex = parseInt(newPrevIndex) + 2;          
         if($(this).hasClass('previous')){               
             $(this).attr('href', newPrevIndex); 
             $('a.next').attr('href', newNextIndex);
         }else{
             $(this).attr('href', newNextIndex); 
             $('a.previous').attr('href', newPrevIndex);
         }         
         var total = $('.poplightBS').length;//$('.imageGallerie').length + 1; 
         if(total === newNextIndex){
             $('a.next').hide();
         }else{
             $('a.next').show()
         }            
         if(newPrevIndex < 0){
             $('a.previous').hide();
         }else{
             $('a.previous').show()
         }         
         return false;
     });
     
/*	$('body').on('click', '.imageGallerie', function() { 
		var ele=this.next('.imageGallerie');
		$('#fade , .popup_block').fadeOut(function() {
			$('#fade, a.close').remove();			
			if(ele.length <= 0) ele=$('.imageGallerie').first();
			ele.trigger( "click" );	
		return false;
	})});	*/
});
(function($) {
	$.fn.shuffle = function() {
		return this.each(function() {
			var items = $(this).children();
			return (items.length) ? $(this).html($.shuffle(items, $(this)))
					: this;
		});
	}

	$.fn.validate = function() {
		var res = false;
		this.each(function() {
			var arr = $(this).children();
			res = ((arr[0].innerHTML == "1") && (arr[1].innerHTML == "2")
					&& (arr[2].innerHTML == "3") && (arr[3].innerHTML == "4")
					&& (arr[4].innerHTML == "5") && (arr[5].innerHTML == "6"));
		});
		return res;
	}

	$.shuffle = function(arr, obj) {
		for (var j, x, i = arr.length; i; j = parseInt(Math.random() * i), x = arr[--i], arr[i] = arr[j], arr[j] = x)
			;
		if (arr[0].innerHTML == "1")
			obj.html($.shuffle(arr, obj))
		else
			return arr;
	}
})(jQuery);

function addslashes(string) {
	return string.replace(/\\/g, '\\\\').
    replace(/\u0008/g, '\\b').
    replace(/\t/g, '\\t').
    replace(/\n/g, '\\n').
    replace(/\f/g, '\\f').
    replace(/\r/g, '\\r').
    replace(/'/g, '\\\'').
    replace(/"/g, '\\"');
	}
		 	
function afficheZone(id)
{
	var a = document.getElementById("svgDoc");
	var svgDoc = a.contentDocument; //get the inner DOM of alpha.svg
    var delta = svgDoc.getElementById("article");
	delta.innerHTML = zoneTexte[id];
}
$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null){
       return null;
    }
    else{
       return results[1] || 0;
    }
}
// Afin de laisser une catégorie ouverte lorsqu'on consulte une de ses pages
$(function() {
	var url = location.href;
	if(((url.indexOf("shopping") > -1)||(url.indexOf("boutique") > -1))&&($.urlParam('id')!="38")&&($.urlParam('id')!="39"))
		{
				var menu_a = $('.menuIndiko > .item1 > a');
			var menu_ul = $('.menuIndiko > .item1 > ul'); // Menu déroulant
			menu_ul.show();
					menu_a.removeClass('active');
	}
	if(((url.indexOf("home") > -1)||(url.indexOf("home") > -1))&&($.urlParam('id')!="22")&&($.urlParam('id')!="23"))
	{
			var menu_a = $('.menuIndiko > .item2 > a');
		var menu_ul = $('.menuIndiko > .item2 > ul'); // Menu déroulant
		menu_ul.show();
				menu_a.removeClass('active');
	}
	   $("#sortable").sortable();
	   $("#sortable").disableSelection();
	   $('#sortable').shuffle();
	$("#formsubmit").click(function() {
		if(!$('ul').validate())
			{alert("Captcha invalide.");return false;			
			}
	});
});