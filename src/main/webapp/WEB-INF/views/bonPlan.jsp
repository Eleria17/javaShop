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
				<spring:url value="/resources/Images/white.jpg" var="white" />
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						Bons Plans 
					</h1>
				</div>
			</div>
			<c:set var="columnProperties" scope="request" />
			<c:set var="columnLabels" scope="request" />
			<c:set var="columnMaxLengths" scope="request" />
			<c:set var="columnTypes" scope="request" />
			<c:set var="columnDatePatterns" scope="request" />
			<div class="row-fluid row-lg-height">
				<c:forEach items="${listePub}" var="item">
					<div class="col-lg-3 colListe" ><div class="itemPagePub" >
				
					
				
					
						<spring:url value="/resources/Images/pub/pub${item.id}.jpg"  var="imageAvatar" />
						 <img class="img-responsive itemImage" alt="Avatar" src="${imageAvatar}"	title="Image" 
							onerror="this.src='${white}'" />
						
					</div></div>
				</c:forEach>
			</div>
		
		</tiles:putAttribute>
	</tiles:insertDefinition>
</jsp:root>
