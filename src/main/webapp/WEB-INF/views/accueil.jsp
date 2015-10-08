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
	<tiles:insertDefinition name="homeTemplate">
		<tiles:putAttribute name="body">
			<div class="row">
				<div class="col-lg-12 carte">
					<spring:url value="/resources/Images/map.svg" var="imageAvatar" />

					<div id="divCarte">
						<object type="image/svg+xml" data="${imageAvatar}" id="svgDoc"
							height="100%" width="100%"> </object>
					</div>
				</div>
			</div>

			<script>
	var zoneTexte = new Array();
	<c:forEach items="${listeZone}" var="item">	
		zoneTexte[ ${item.id} ]=" ${fn:replace(item.texte,'"', '\\"')} ";
	</c:forEach>
			</script>
			
		</tiles:putAttribute>
	</tiles:insertDefinition>
</jsp:root>