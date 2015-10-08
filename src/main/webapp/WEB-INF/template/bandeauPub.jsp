	<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:tiles="http://tiles.apache.org/tags-tiles"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:output omit-xml-declaration="yes" />

	
<a href="bonPlan"><b><c:out value="${phraseBP}"/></b></a>	
<br/>	

<c:forEach items="${publicites}" var="item">
<spring:url value="/resources/Images/pub/pub${item.id}.jpg" var="imageArticle" />
				<a href="${item.lien}"><img src="${imageArticle}" class="pub"/></a>	<br/>
						</c:forEach>
						
		

</jsp:root>