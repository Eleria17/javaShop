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
<!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Indiko
                    <small>La Rochelle</small>
                </h1>
            </div>
        </div>
        <!-- /.row -->
			<c:set var="columnProperties" scope="request" />
			<c:set var="columnLabels" scope="request" />
			<c:set var="columnMaxLengths" scope="request" />
			<c:set var="columnTypes" scope="request" />
			<c:set var="columnDatePatterns" scope="request" />
			<div class="row-fluid row-lg-height">
				<c:forEach items="${listeHistoire}" var="item">
					<div class="col-lg-4 colListe" ><div class="itemPage" >
					<div class="itemHaut">	<H4 style="margin :0;"> <a href="boutique?id=${item.id}">
							<c:out
								value="${item.nom}" /></a></H4></div>
					<div class="itemGauche">
				
						<a href="boutique?id=${item.id}"> 
						<spring:url value="/resources/Images/boutiques/${item.id}.jpg" var="imageAvatar" />
						 <img class="img-responsive itemImage" alt="Avatar" src="${imageAvatar}"	title="Image" 
							onerror="this.src='${white}'" /> </a>
							</div><div class="itemDroit">
						<small>${item.textecourt}  </small><br/>&#160;</div>
					</div></div>
				</c:forEach>
			</div>

		</tiles:putAttribute>
	</tiles:insertDefinition>
</jsp:root>
