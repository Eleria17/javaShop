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
						Commande <small>récapitulatif</small>
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


<div style="float:left;width:50%">
<c:if test="${!paiement}">
<form id="frm" action="${actionURL}">
<input type="hidden" name="cmd" value="_xclick"/>
<input type="hidden" name="item_name" value="Indiko"/>
<input type="hidden" name="image_url" value="http://indiko.fr/Indiko/resources/Images/logo.png"/>
<input type="hidden" name="image" value="http://indiko.fr/Indiko/resources/Images/logo.png"/>
<input type="hidden" name="business" value="${businessKey}"/>
<input type="hidden" name="return" value="http://indiko.fr/validationCommande"/>
  <input type="hidden" name="notify_url" value="http://indiko.fr/validationCommande"/>
  <input type='hidden' name='rm' value='2'/>
<input type="hidden" name="cancel_return" value="http://indiko.fr/panier"/>
<input type="hidden" name="currency_code" value="EUR"/>
<input type="hidden" name="item_name" value="commande Indiko"/>
<input type="hidden" name="amount" value="${montant}"/>
<input type="submit" value="Payer"></input>
</form>
</c:if>
<c:if test="${paiement}">
<b>
${messagePaiement}
</b>
</c:if>
</div>
		</tiles:putAttribute>
	</tiles:insertDefinition>
</jsp:root>
