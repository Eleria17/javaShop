<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:tiles="http://tiles.apache.org/tags-tiles"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:output omit-xml-declaration="yes" />

	<jsp:directive.page contentType="text/html; charset=utf8"   pageEncoding="utf8"   />

	<tiles:insertDefinition name="defaultTemplate">
		<tiles:putAttribute name="body">
				<spring:url value="/resources/Images/white.jpg" var="white" />
				
			<div class="row">
				<div class="col-lg-5">
	
			
					<h1 class="page-header"><small>
					<a href="home?id=${IDcategorieparent}">	${NomCategorieParent} </a> <img src="resources/Images/fleche-chemin.png" class="cheminImg" /> &#x00a0;
					<a href="home?id=${pageHistoire.categoriehistoire}">	${NomCategorie} </a> <img src="resources/Images/fleche-chemin.png" class="cheminImg" /> &#x00a0;${pageHistoire.nom}</small>
					</h1></div>	<div class="col-lg-6 etoileNota">
						<c:choose>
				<c:when test="${note==1}">
					<img src="resources/Images/starON.png" class="star" id="star1" />
					<img src="resources/Images/starOFF.png" class="star" id="star2" />
					<img src="resources/Images/starOFF.png" class="star" id="star3" />
					<img src="resources/Images/starOFF.png" class="star" id="star4" />
					<img src="resources/Images/starOFF.png" class="star" id="star5" />
				</c:when>
				<c:when test="${note==2}">
					<img src="resources/Images/starON.png" class="star" id="star1" />
					<img src="resources/Images/starON.png" class="star" id="star2" />
					<img src="resources/Images/starOFF.png" class="star" id="star3" />
					<img src="resources/Images/starOFF.png" class="star" id="star4" />
					<img src="resources/Images/starOFF.png" class="star" id="star5" />
				</c:when>
				<c:when test="${note==3}">
					<img src="resources/Images/starON.png" class="star" id="star1" />
					<img src="resources/Images/starON.png" class="star" id="star2" />
					<img src="resources/Images/starON.png" class="star" id="star3" />
					<img src="resources/Images/starOFF.png" class="star" id="star4" />
					<img src="resources/Images/starOFF.png" class="star" id="star5" />
				</c:when>
				<c:when test="${note==4}">
					<img src="resources/Images/starON.png" class="star" id="star1" />
					<img src="resources/Images/starON.png" class="star" id="star2" />
					<img src="resources/Images/starON.png" class="star" id="star3" />
					<img src="resources/Images/starON.png" class="star" id="star4" />
					<img src="resources/Images/starOFF.png" class="star" id="star5" />
				</c:when>
				<c:otherwise>
					<img src="resources/Images/starON.png" class="star" id="star1" />
					<img src="resources/Images/starON.png" class="star" id="star2" />
					<img src="resources/Images/starON.png" class="star" id="star3" />
					<img src="resources/Images/starON.png" class="star" id="star4" />
					<img src="resources/Images/starON.png" class="star" id="star5" />
				</c:otherwise>
			</c:choose>
				</div>
			</div>
			<div class="row-fluid">
				<div class="col-lg-6 imgPrinCol"  style="padding:0;background-color:black;">
				
					<spring:url
						value="/resources/Images/culture/${pageHistoire.id}.jpg"
						var="imageAvatar" />
					<img class="img-responsive illusPrincipal poplightBS" alt="Avatar" src="${imageAvatar}"
						title="Image" onerror="this.src='${white}'" />
				</div>
				<div class="col-lg-6 carteCol"  style="padding:0;">
				<c:if test="${pageHistoire.localisation_x>0}">
					<div id="map-canvas">&#x00a0;</div>
					</c:if>
				<c:if test="${pageHistoire.localisation_x==0}">
					<div id="map-vide">&#x00a0;</div>
					</c:if>
				</div>
			</div>
			<br />
			<div class="row  row-lg-height">
			<div class="col-lg-6">
			<c:forEach items="${gallerie}" var="item" varStatus="count">
				
				<a href="#" data-width="500" data-rel="popup${count.index}" class=" aGallerie"><img
						src="resources/Images/culture/${pageHistoire.id}_gallerie/${item}" class="poplightBS galleriePage" /></a>
									
			</c:forEach>
			</div>	
				<div class="col-lg-6 resumeDiv">
				<div class="resumeLieu">
				<div  id="ResumeLieuCourt">
					${pageHistoire.texte}</div></div>
					
				<a href="#" id="suiteArticle">suite...</a>
				</div>
			</div>.

			<script type="text/javascript">
var map;
function initialize() {
  var mapOptions = {    zoom: 15,    center: new google.maps.LatLng(<c:out value='${pageHistoire.localisation_x}'/>  ,	<c:out value='${pageHistoire.localisation_y}'/> )  };
  map = new google.maps.Map(document.getElementById('map-canvas'),      mapOptions);
var maPos = new google.maps.LatLng(<c:out value='${pageHistoire.localisation_x}'/>  ,	<c:out value='${pageHistoire.localisation_y}'/> );
var marker = new google.maps.Marker({   position: maPos,   map: map,   title: "addslashes(${pageHistoire.nom}) " });

}
google.maps.event.addDomListener(window, 'load', initialize);
var articleId =<c:out value='${pageHistoire.id}'/>;
var type=2;
</script>

<div class="row  row-lg-height">
			<div class="col-lg-12">
			
			<c:forEach items="${gallerie}" var="item" varStatus="compte">
			<div id="popup${compte.index}" class="popup_Img popup_block imageGallerie">
				<img src="resources/Images/culture/${pageHistoire.id}_gallerie/${item}" class="popupImage" />
			</div>
</c:forEach>

		
			<br />
						<a href="#" id="affichCommenter">Commenter</a>
			<div id="commenterDiv">
			<form method="post" action="creeCommentaire">
				<div class="formCom">Nom:</div>
				<input type='text' name='name' id='name' /><br />

				<div class="formCom">Email:</div>
				<input type='text' name='email' id='email' /><br />

				<textarea name="comment" id="comment">Commentaire</textarea>
				<br /> <input type='hidden' name='pageId' id='pageId'
					value="${pageHistoire.id}" />
					 <fieldset>
                <legend>Veuillez mettre les chiffres dans le bon ordre.</legend>
                <div class="captcha_wrap">
                    <div class="captcha">
			Verification :
                    </div>
                    <ul id="sortable">
                        <li class="captchaItem">1</li>
                        <li class="captchaItem">2</li>
                        <li class="captchaItem">3</li>
                        <li class="captchaItem">4</li>
                        <li class="captchaItem">5</li>
                        <li class="captchaItem">6</li>
                    </ul>
                </div>
              
            </fieldset>
					 <input type='submit'  id="formsubmit" value="Envoyer" />
			</form>
			</div>
			<br />
			<c:forEach items="${commentaires}" var="item">
				<div class="formCom">
					<b>${item.nom}</b>
				</div> ${item.texte} <br />
			</c:forEach>
			<br />
			</div></div>
			

     
		</tiles:putAttribute>
	</tiles:insertDefinition>
	    <div class="row">
			<div class="col-lg-12">
			 <div class="modal fade  bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">  
     <!--  <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h4 class="modal-title"></h4>
            </div>   -->       
          <div class="modal-body">                
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
     </div>
     </div>
</jsp:root>
