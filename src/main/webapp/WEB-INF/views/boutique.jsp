<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:tiles="http://tiles.apache.org/tags-tiles"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:output omit-xml-declaration="yes" />

	<jsp:directive.page contentType="text/html; charset=utf8" />

	<tiles:insertDefinition name="defaultTemplate">
		<tiles:putAttribute name="body">
			<!-- Page Header -->
			<div class="row">
				<div class="col-lg-6">
					<h1 class="page-header">
						<small> <a href="shopping?id=${IDcategorieparent}">${NomCategorieParent}</a>
							&#x00a0; <img src="resources/Images/fleche-chemin.png"
							class="cheminImg" />&#x00a0; <a
							href="shopping?id=${boutique.categorie}">${NomCategorie}</a>
							&#x00a0; <img src="resources/Images/fleche-chemin.png"
							class="cheminImg" />&#x00a0; ${boutique.nom}
						</small>
					</h1>
				</div>
				<div class="col-lg-6 etoileNota">
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
			<div class="row  row-lg-height">
				<div class="col-lg-6 imgPrinCol"
					style="padding: 0; background-color: black;">

					<spring:url value="resources/Images/boutiques/${boutique.id}.jpg"
						var="imageAvatar" />
					<img class="poplightBS img-responsive illusPrincipal" alt="Avatar"
						src="${imageAvatar}" title="Image"
						onerror="this.src='/DFRoo/resources/images/white.jpg'" />

				</div>
				<div class="col-lg-6" style="padding: 0;">
					<div id="map-canvas">Carte</div>
				</div>
			</div>
			<br />
			<div class="row  row-lg-height">
				<div class="col-lg-6 ratioGallerie col-xs-12 col-sm-12" style="padding: 0">
					<c:forEach items="${gallerie}" var="item" varStatus="count">
						<div class="imageGallerie">
							<spring:url
								value="resources/Images/boutiques/${boutique.id}_gallerie/${item}"
								var="imageAvatar" />
							<a href="#" data-width="500" data-rel="popup${count.index}"
								class="aGallerie"><img src="${imageAvatar}"
								class="poplightBS galleriePage" /></a>
						</div>
					</c:forEach>
				</div>
				<div class="col-lg-6 resumeDiv col-xs-12 col-sm-12">${boutique.texte}</div>
			</div>
			<c:if test="${fn:length(articles)>0}">
			<div class="row  row-lg-height">
				<div class="col-lg-12 ratioGallerie  col-xs-12 col-sm-12">
					<h4 style="color: #34BCFA; text-align: center">${boutiqueLigne}</h4>
					<hr
						style="margin: 2px; height: 2px; border-top: 2px solid #34BCFA;"></hr>
					<c:forEach items="${articles}" var="item">
						<spring:url value="/resources/Images/articles/${item.id}.jpg"
							var="imageArticle" />
						<a href="#" data-width="500" data-rel="popupA${item.id}"
							class="poplight lienArticle"><img src="${imageArticle}"
							class="img-responsive itemArticle gallerieArticle" />
							<fmt:formatNumber  type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.prix}"/> 
							 €</a>
					</c:forEach>
				</div>
				<div class="col-lg-6  col-xs-12 col-sm-12">&#x00a0; </div>
			</div>
			</c:if>
			<script type="text/javascript">
                var map;
                function initialize() {
                    var mapOptions = {
                        zoom: 15,
                        center: new google.maps.LatLng( <c:out value='${loop.index}'/>
                        <c:out value='${boutique.localisation_x}'/>,
                        	<c:out value=' ${boutique.localisation_y}'/>)
                    };
                    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
                    var maPos = new google.maps.LatLng(<c:out value='${boutique.localisation_x}'/>, <c:out value='${boutique.localisation_y}'/>);
                    var marker = new google.maps.Marker({position: maPos, map: map, title: " addslashes(${boutique.nom}) "});
                }
                google.maps.event.addDomListener(window, 'load', initialize);
                var articleId =${boutique.id};
                var type = 3;
            </script>
			<!-- 	<div class="row  row-lg-height">
            <div class="col-lg-12">
            <c:forEach items="${gallerie}" var="item" varStatus="compte">
            <div id="popup${compte.index}" class=" popup_Img popup_block">
                    <img src="resources/Images/boutiques/${boutique.id}_gallerie/${item}" class="popupImage" />
                    </div>
            </c:forEach>
            </div>
            </div>-->
			<br />
			<c:forEach items="${articles}" var="item">
				<spring:url value="/resources/Images/articles/${item.id}.jpg"
					var="imageArticle" />
				<div id="popupA${item.id}" class="popup_block">
					<jsp:include page="article.jsp">
						<jsp:param name="idArticle" value="${item.id}" />
						<jsp:param name="titreArticle" value="${item.nom}" />
						<jsp:param name="texteArticle" value="${item.texte}" />
						<jsp:param name="prixArticle" value="${item.prix}" />
					</jsp:include>
				</div>
			</c:forEach>
			<br />
		</tiles:putAttribute>
	</tiles:insertDefinition>
	<div class="row">
		<div class="col-lg-12">
			<div class="modal fade  bs-example-modal-lg " id="myModal"
				tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<!--  <div class="modal-header">
                                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                                   <h4 class="modal-title"></h4>
                               </div>   -->
						<div class="modal-body"></div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>
	</div>
</jsp:root>
