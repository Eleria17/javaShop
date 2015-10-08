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
				<div class="col-lg-12">
					<h1 class="page-header">
						Shopping <small>Mon Panier</small>
					</h1>
				</div>
			</div>
						<table  class="responstable">
			<tr>
                <th></th>
                <th>Nom</th>
               
                <th>Prix unitaire</th> <th>Quantité</th>
                <th>Total</th>
            </tr>
	<c:forEach items="${panier.objArticles}" var="item"  varStatus="theCount"><spring:url value="/resources/Images/articles/${item.id}.jpg" var="imageArticle" />
		<tr>		<td>	<img	src="${imageArticle}"  class="img-responsive itemArticle" /> </td>
						<td>${item.nom} </td> <td> <fmt:formatNumber  type="number" value="${item.prix}"/> € </td><td>${panier.quantites[theCount.index]}</td><td> <fmt:formatNumber var="totalT"
  value="${item.prix*panier.quantites[theCount.index]}"
  maxFractionDigits="2" /><fmt:formatNumber  type="number" value="${totalT}"/> €</td></tr>
						</c:forEach>
			<tr>	<td colspan="5"> Frais de port : 
		<c:if test="${charenteMaritime}">	<b>Gratuit</b> en charente maritime </c:if>
	<c:if test="${!charenteMaritime}">		 <fmt:formatNumber  type="number" value="${panier.fraisPort}"/>€ en France Metrolitaine </c:if>
			 </td></tr>	
		<tr>	<td> </td>	<td> </td><td> </td><td> </td><td><fmt:formatNumber  type="number" minFractionDigits="2" maxFractionDigits="2" value="${panier.totalHt}"/> €</td></tr>	
</table>
<div style="float:left;width:50%"> Se connecter <br/>
    <form action="login" id="login" method="get">
     <input type='text' name='username' id="loginEmail" placeholder="Email"/>
          <input type='password' name='password'  id="loginPass"  placeholder="Mot de passe"/>            
              <input name="submit" type="submit" value="Login"/>
           </form> </div>

<div style="float:left;width:50%"> S'enregistrer<br/> <a href="MonCompte"><b>Mon Compte</b></a></div>
		</tiles:putAttribute>
	</tiles:insertDefinition>
</jsp:root>
