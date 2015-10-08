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

    <fmt:parseNumber var="i" type="number" value="${param.idArticle}" />
    <spring:url value="/resources/Images/white.jpg" var="white" />
    <!-- Page Header -->
    <div class="row">
        <div class="col-lg-12  col-xs-12 col-sm-12">
            <h3 class="page-header">
                ${param.titreArticle}
            </h3>
        </div>
    </div>
    <div class="row-fluid">
        <div class="col-lg-5 col-xs-5 col-sm-5">
            <BR />
            <spring:url
                value="/resources/Images/articles/${param.idArticle}.jpg"						var="imageAvatar" />
            <img class="popImgArticle img-responsive " alt="Avatar" src="${imageAvatar}"	title="Image" id="${param.idArticle}principal"	onerror="this.src='${white}'" />
            <c:forEach items="${gallerieB[i]}" var="item" varStatus="count">
                <!--  poplightArt -->
                <a href="#" data-width="500" data-rel="popupB${i+count.index}" class=" aGallerie"> <img
                        src="resources/Images/articles/${i}_gallerie/${item}" class="poplightArt galleriePage" data-imgprin="${param.idArticle}principal" />    </a> 

            </c:forEach>
        </div>
        <div class="col-lg-5 col-xs-5 col-sm-5">
            ${param.texteArticle} <br/>
            <H1>
            <fmt:formatNumber  type="number" minFractionDigits="2" maxFractionDigits="2" value="${param.prixArticle}"/> 
            €</H1>          
        </div>
        <div class="col-lg-2  col-xs-2 col-sm-2">

        <c:forEach items="${articleSug[i]}" var="item">
            <spring:url value="/resources/Images/articles/${item.id}.jpg" var="imageArticle" />
            <a href="boutique?id=${item.boutique}">
            <img src="${imageArticle}" class="pubArt"/></a><br/>
        </c:forEach>
    </div>
    <br/></div> <div class="row-fluid">
    <div class="col-lg-12  col-xs-12 col-sm-12"><br/>
           <form action="ajoutPanier" name="input" method="get"  style="/*bottom:100px;right: 25%;position: fixed;*/"><input type="hidden" value="${param.idArticle}" name="idArticle"/>
            <input type="number" value="1" name="quantite"/><input type="submit" value="Ajouter au panier"/></form>
        </div>
    
    </div> <br/><br/>
    <!--  			<img src="resources/Images/starON.png" class="star" id="star1" />
                            <img src="/app/resources/Images/starON.png" class="star" id="star2" />
                            <img src="/app/resources/Images/starON.png" class="star" id="star3" />
                            <img src="/app/resources/Images/starOFF.png" class="star" id="star4" />
                            <img src="/app/resources/Images/starOFF.png" class="star" id="star5" />
    <c:forEach items="${gallerieB[i]}" var="item" varStatus="compte">
        <div id="popupB${i+compte.index}" class="popup_Img popup_blockArt">
            <img src="resources/Images/articles/${i}_gallerie/${item}" class="popupImage" />
        </div>
    </c:forEach>-->
</jsp:root>
